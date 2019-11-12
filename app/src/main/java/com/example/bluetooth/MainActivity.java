package com.example.bluetooth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class MainActivity extends AppCompatActivity {
    public static final int END = 0;
    public static final int RESET = 1;


    private BluetoothSPP bt;
    TextView textElement;
    TextView textBraille;
    TextView textState;
    Button btnReset;
    Button btnSend;
    Switch btnState;
    EditText textSend;

    Thread sendthread;
    SendThread st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.btnReset);
        btnSend = findViewById(R.id.btnSend);
        btnState = findViewById(R.id.btnState);

        textState = findViewById(R.id.textState);
        textElement = findViewById(R.id.textElement);
        textBraille = findViewById(R.id.textBraille);
        textSend = findViewById(R.id.textSend);
        bt = new BluetoothSPP(this); //Initializing
        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(getApplicationContext()
                    , "블루투스 사용 불가"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            Button btnConnect = findViewById(R.id.btnConnect);

            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
                btnState.setChecked(true);
                textState.setText("연결 성공, 입력 대기");
                btnSend.setEnabled(true);
                textSend.setEnabled(true);
                btnConnect.setText("연결해제");
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
                btnState.setChecked(false);
                textState.setText("연결 해제");
                btnSend.setEnabled(false);
                btnReset.setEnabled(false);
                textSend.setEnabled(false);
                btnConnect.setText("연결");
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
                btnState.setChecked(false);
                textState.setText("연결 실패");
                btnSend.setEnabled(false);
                btnReset.setEnabled(false);
                textSend.setEnabled(false);
                btnConnect.setText("연결");
            }
        });

        Button btnConnect = findViewById(R.id.btnConnect); //연결시도

        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
//                Toast.makeText(MainActivity.this, "수신 성공", Toast.LENGTH_SHORT).show();
                if (message.contains("q")) {
                    sendthread.interrupt();
                    textState.setText("아두이노 통신 중");
                }
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st.onReset();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset.setEnabled(true);
                btnSend.setEnabled(false);
                ArrayList<List> braille_list = H2b.convert(textSend.getText().toString());

                textState.setText("아두이노 신호 대기");
                st = new SendThread(braille_list);
                sendthread = new Thread(st);
                sendthread.start();
            }
        });
    }

    class SendThread implements Runnable {
        ArrayList<String> binary_data = new ArrayList<>();
        ArrayList<String> element_data = new ArrayList<>();
        ArrayList<String> braille_data = new ArrayList<>();
        ArrayList space_braille = new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
        ArrayList<List> braille_list;
        boolean stop;

        int braille_length = 0;
        int max_braille_length = 4;

        SendThread(ArrayList<List> braille_list) {
            this.braille_list = braille_list;
            this.stop = false;
        }
        void onSend() {
            String element_str = "";
            String braille_str = "";
            String binary_str = "";
            for (int j = 0; j < binary_data.size(); j++) {
                binary_str += binary_data.get(j);
                element_str += element_data.get(j);
                braille_str += braille_data.get(j) + " ";
            }
            Log.i("", "데이터 전송");
            textElement.setText(element_str);
            textBraille.setText(braille_str);

            bt.send(binary_str, true);
            Log.i("", "SendThread 대기 시작");
            while (!Thread.currentThread().isInterrupted());
            Log.i("", "SendThread 대기 종료");
            Thread.currentThread().interrupted();
        }
        void onEnd(){
            bt.send("Q", true);
            handler.sendEmptyMessage(END);
        }
        void onReset(){
            this.onEnd();
            this.stop = true;
            sendthread.interrupt();
        }
        public void run() {
            Log.i("state", "SendThread 시작");
            for (int i = 0; i < braille_list.size() && !this.stop ; i++) {

                String element = (String) braille_list.get(i).get(0);
                String binary = (String) braille_list.get(i).get(1);
                int length = (int) braille_list.get(i).get(2);
                String braille = String.valueOf(braille_list.get(i).get(3));

                if (i == braille_list.size() - 1) {
                    for (int j = 0; j < max_braille_length - braille_length; j++) {
                        element_data.add((String) space_braille.get(0));
                        binary_data.add((String) space_braille.get(1));
                        braille_data.add(String.valueOf(space_braille.get(3)));
                    }
                    onSend();
                    onEnd();
                }
                if (braille_length + length < max_braille_length) {
                    braille_length += length;
                    binary_data.add(binary);
                    element_data.add(element);
                    braille_data.add(braille);
                } else if (braille_length + length == max_braille_length) {
                    binary_data.add(binary);
                    element_data.add(element);
                    braille_data.add(braille);
                    onSend();

                    braille_length = 0;
                    binary_data = new ArrayList<>();
                    element_data = new ArrayList<>();
                    braille_data = new ArrayList<>();

                } else if (braille_length + length > max_braille_length) {
                    for (int j = 0; j < max_braille_length - braille_length; j++) {
                        element_data.add((String) space_braille.get(0));
                        binary_data.add((String) space_braille.get(1));
                        braille_data.add(String.valueOf(space_braille.get(3)));
                    }
                    onSend();

                    braille_length = length;
                    binary_data = new ArrayList<>();
                    element_data = new ArrayList<>();
                    braille_data = new ArrayList<>();

                    binary_data.add(binary);
                    element_data.add(element);
                    braille_data.add(braille);
                }
            }
            Log.i("state", "SendThread 종료");
        }
    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case END:
                    textElement.setText("전송 종료");
                    textBraille.setText("전송 종료");
                    btnReset.setEnabled(false);
                    btnSend.setEnabled(true);
                    Log.i("state","END");
                    break;
                case RESET:
                    textElement.setText("전송 취소");
                    textBraille.setText("전송 취소");
                    btnReset.setEnabled(false);
                    btnSend.setEnabled(true);
                    Log.i("state","RESET");
                    break;
                default:
                    break;
            }
        }
    };

    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
