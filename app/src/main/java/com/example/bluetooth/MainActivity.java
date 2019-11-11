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
    public static final int SEND_INFORMATION = 0;
    public static final int SEND_STOP = 1;
    private BluetoothSPP bt;
    SendThread thread;
    TextView textElement;
    TextView textBraille;
    TextView textState;
    Button btnNext;
    Button btnChange;
    Switch btnState;
    EditText textSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.btnNext);
        btnChange = findViewById(R.id.btnSend);
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

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신

            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                if (message.contains("q")) {
                    handler.sendEmptyMessage(SEND_STOP);
                    textState.setText("아두이노 신호 대기");
                }
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            Button btnConnect = findViewById(R.id.btnConnect);

            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
                btnState.setChecked(true);
                textState.setText("연결 성공, 입력 대기");
                btnChange.setEnabled(true);
                btnNext.setEnabled(true);
                textSend.setEnabled(true);
                btnConnect.setText("연결해제");
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
                btnState.setChecked(false);
                textState.setText("연결 해제");
                btnChange.setEnabled(false);
                btnNext.setEnabled(false);
                textSend.setEnabled(false);
                btnConnect.setText("연결");
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
                btnState.setChecked(false);
                textState.setText("연결 실패");
                btnChange.setEnabled(false);
                btnNext.setEnabled(false);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(SEND_STOP);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textSend.getText().toString();
                thread = new SendThread(str);
                thread.start();
            }
        });
    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_INFORMATION:
                    bt.send((String) msg.obj, true);
                    break;

                case SEND_STOP:
                    thread.interrupt();
                    break;

                default:
                    break;
            }
        }
    };

    class SendThread extends java.lang.Thread {
        String str;
        ArrayList<List> braille_list;
        ArrayList<String> binary_data = new ArrayList<>();
        ArrayList<String> element_data = new ArrayList<>();
        ArrayList<String> braille_data = new ArrayList<>();
        ArrayList space_braille = new ArrayList<>(Arrays.asList("\\", "000000/", 1, "⣿"));

        int braille_length = 0;
        int max_braille_length = 4;

        Message message;

        public SendThread(String str) {
            this.str = str;
        }

        public void onSend() {
            String element_str = "";
            String braille_str = "";
            String binary_str = "";

            for (int j = 0; j < binary_data.size(); j++) {
                binary_str += binary_data.get(j);
                element_str += element_data.get(j);
                braille_str += braille_data.get(j) + " ";
            }

            textElement.setText(element_str);
            textBraille.setText(braille_str);

            message = handler.obtainMessage();
            message.what = SEND_INFORMATION;
            message.obj = binary_str;
            handler.sendMessage(message);

            while (!Thread.currentThread().isInterrupted()) {
            }
            thread.interrupted();
        }

        public void onEnd() {
            textState.setText("전송 끝, 입력 대기");
            textElement.setText("전송 완료");
            textBraille.setText("전송 완료");

            message = handler.obtainMessage();
            message.what = SEND_INFORMATION;
            message.obj = "000000/000000/000000/000000/";
            handler.sendMessage(message);

            while (!Thread.currentThread().isInterrupted()) {
            }
            thread.interrupted();
        }

        public void run() {
            super.run();
            String[] word;
            word = str.split(" ");

            ArrayList<List> braille_list = new ArrayList();
            List temp;

            Log.i("state", "변환중");
            for (int i = 0; i < word.length; i++) {
                String voca = word[i]; // 단어 저장
                if (H2b.abbr_Condition(voca)) {
                    Log.i("약어 단어 O - ", voca);
                    braille_list.add(H2b.abbr_H2b(voca));
                } else {
                    // 약어 단어 X
                    for (int j = 0; j < voca.length(); j++) {
                        String letter = String.valueOf(voca.charAt(j)); // 글자 저장
                        // 약어 글자 O
                        if (H2b.abbr_Condition(letter)) {
                            Log.i("약어 글자 O - ", letter);
                            braille_list.add(H2b.abbr_H2b(letter));
                        }
                        // 약어 글자 X
                        else {
                            if (H2b.getType(letter)) {
                                Log.i("한글 글자 O - ", letter);
                                if (H2b.splitJaso(letter).length() == 3) {
                                    Log.i("종성O - ", letter);
                                    String element = H2b.splitJaso(letter);
                                    temp = H2b.cho_H2b(element.charAt(0));
                                    braille_list.add(temp);

                                    temp = H2b.joong_H2b(element.charAt(1));
                                    braille_list.add(temp);

                                    temp = H2b.jong_H2b(element.charAt(2));
                                    braille_list.add(temp);
                                } else {
                                    Log.i("종성X - ", letter);
                                    String element = H2b.splitJaso(letter);
                                    temp = H2b.cho_H2b(element.charAt(0));
                                    braille_list.add(temp);

                                    temp = H2b.joong_H2b(element.charAt(1));
                                    braille_list.add(temp);
                                }
                            } else {
                                Log.i("한글X - ", letter);
                                String element = H2b.splitJaso(letter);
                                temp = H2b.no_han_H2b(element.charAt(0));
                                braille_list.add(temp);
                            }
                        }
                    }
                    braille_list.add(space_braille);
                }
            }
            textState.setText("아두이노 신호 대기");
            for (int i = 0; i < braille_list.size(); i++) {
                String element = (String) braille_list.get(i).get(0);
                String binary = (String) braille_list.get(i).get(1);
                int length = (int) braille_list.get(i).get(2);
                String braille = String.valueOf(braille_list.get(i).get(3));

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

                if (i == braille_list.size() - 1) {
                    for (int j = 0; j < max_braille_length - braille_length; j++) {
                        element_data.add((String) space_braille.get(0));
                        binary_data.add((String) space_braille.get(1));
                        braille_data.add(String.valueOf(space_braille.get(3)));
                    }

                    onSend();
                    braille_length = 0;
                    element_data = new ArrayList<>();
                    binary_data = new ArrayList<>();
                    braille_data = new ArrayList<>();
                }
            }
            onEnd();
        }
    }


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
