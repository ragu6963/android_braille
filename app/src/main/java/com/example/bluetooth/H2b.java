package com.example.bluetooth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class H2b {
    private final static char[] ChoSung = {0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};
    private final static char[] JwungSung = {0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163};
    private final static char[] JongSung = {0, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};

    static List cho_H2b(char c) {
        if (c == 'ㄱ') return new ArrayList<>(Arrays.asList("ㄱ", "000100/", 1, "⠈"));
        if (c == 'ㄴ') return new ArrayList<>(Arrays.asList("ㄴ", "100100/", 1, "⠉"));
        if (c == 'ㄷ') return new ArrayList<>(Arrays.asList("ㄷ", "010100/", 1, "⠊"));
        if (c == 'ㄹ') return new ArrayList<>(Arrays.asList("ㄹ", "000010/", 1, "⠐"));
        if (c == 'ㅁ') return new ArrayList<>(Arrays.asList("ㅁ", "100010/", 1, "⠑"));
        if (c == 'ㅂ') return new ArrayList<>(Arrays.asList("ㅂ", "000110/", 1, "⠘"));
        if (c == 'ㅅ') return new ArrayList<>(Arrays.asList("ㅅ", "000001/", 1, "⠠"));
        if (c == 'ㅇ') return new ArrayList<>(Arrays.asList("ㅇ", "011011/", 1, "⠶"));
        if (c == 'ㅈ') return new ArrayList<>(Arrays.asList("ㅈ", "000101/", 1, "⠨"));
        if (c == 'ㅊ') return new ArrayList<>(Arrays.asList("ㅊ", "000011/", 1, "⠰"));
        if (c == 'ㅋ') return new ArrayList<>(Arrays.asList("ㅋ", "110100/", 1, "⠋"));
        if (c == 'ㅌ') return new ArrayList<>(Arrays.asList("ㅌ", "110010/", 1, "⠓"));
        if (c == 'ㅍ') return new ArrayList<>(Arrays.asList("ㅍ", "100110/", 1, "⠙"));
        if (c == 'ㅎ') return new ArrayList<>(Arrays.asList("ㅎ", "010110/", 1, "⠚"));
        if (c == 'ㄲ') return new ArrayList<>(Arrays.asList("ㄲ", "000001/000100/", 2, "⠠⠈"));
        if (c == 'ㄸ') return new ArrayList<>(Arrays.asList("ㄸ", "000001/010100/", 2, "⠠⠊"));
        if (c == 'ㅃ') return new ArrayList<>(Arrays.asList("ㅃ", "000001/000110/", 2, "⠠⠘"));
        if (c == 'ㅆ') return new ArrayList<>(Arrays.asList("ㅆ", "000001/000001/", 1, "⠠⠠"));
        if (c == 'ㅉ') return new ArrayList<>(Arrays.asList("ㅉ", "000001/000101/", 2, "⠠⠨"));
        return new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
    }

    public static List joong_H2b(char c) {
        if (c == 'ㅏ') return new ArrayList<>(Arrays.asList("ㅏ", "110001/", 1, "⠣"));
        if (c == 'ㅑ') return new ArrayList<>(Arrays.asList("ㅑ", "001110/", 1, "⠜"));
        if (c == 'ㅓ') return new ArrayList<>(Arrays.asList("ㅓ", "011100/", 1, "⠎"));
        if (c == 'ㅕ') return new ArrayList<>(Arrays.asList("ㅕ", "100011/", 1, "⠱"));
        if (c == 'ㅗ') return new ArrayList<>(Arrays.asList("ㅗ", "101001/", 1, "⠥"));
        if (c == 'ㅛ') return new ArrayList<>(Arrays.asList("ㅛ", "001101/", 1, "⠬"));
        if (c == 'ㅜ') return new ArrayList<>(Arrays.asList("ㅜ", "101100/", 1, "⠍"));
        if (c == 'ㅠ') return new ArrayList<>(Arrays.asList("ㅠ", "100101/", 1, "⠩"));
        if (c == 'ㅡ') return new ArrayList<>(Arrays.asList("ㅡ", "010101/", 1, "⠪"));
        if (c == 'ㅣ') return new ArrayList<>(Arrays.asList("ㅣ", "101010/", 1, "⠕"));
        if (c == 'ㅐ') return new ArrayList<>(Arrays.asList("ㅐ", "111010/", 1, "⠗"));
        if (c == 'ㅔ') return new ArrayList<>(Arrays.asList("ㅔ", "101110/", 1, "⠝"));
        if (c == 'ㅒ') return new ArrayList<>(Arrays.asList("ㅒ", "001110/111010/", 2, "⠜⠗"));
        if (c == 'ㅖ') return new ArrayList<>(Arrays.asList("ㅖ", "001100/", 1, "⠌"));
        if (c == 'ㅘ') return new ArrayList<>(Arrays.asList("ㅘ", "111001/", 1, "⠧"));
        if (c == 'ㅙ') return new ArrayList<>(Arrays.asList("ㅙ", "111001/111010/", 2, "⠧⠗"));
        if (c == 'ㅚ') return new ArrayList<>(Arrays.asList("ㅚ", "101111/", 1, "⠽"));
        if (c == 'ㅝ') return new ArrayList<>(Arrays.asList("ㅝ", "111100/", 1, "⠏"));
        if (c == 'ㅞ') return new ArrayList<>(Arrays.asList("ㅞ", "111100/111010/", 2, "⠏⠗"));
        if (c == 'ㅟ') return new ArrayList<>(Arrays.asList("ㅟ", "101100/111010/", 2, "⠍⠗"));
        if (c == 'ㅢ') return new ArrayList<>(Arrays.asList("ㅢ", "010111/", 1, "⠺"));
        return new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
    }

    static List jong_H2b(char c) {
        if (c == 'ㄱ') return new ArrayList<>(Arrays.asList("ㄱ", "100000/", 1, "⠁"));
        if (c == 'ㄴ') return new ArrayList<>(Arrays.asList("ㄴ", "010010/", 1, "⠒"));
        if (c == 'ㄷ') return new ArrayList<>(Arrays.asList("ㄷ", "001010/", 1, "⠔"));
        if (c == 'ㄹ') return new ArrayList<>(Arrays.asList("ㄹ", "010000/", 1, "⠂"));
        if (c == 'ㅁ') return new ArrayList<>(Arrays.asList("ㅁ", "010001/", 1, "⠢"));
        if (c == 'ㅂ') return new ArrayList<>(Arrays.asList("ㅂ", "110000/", 1, "⠃"));
        if (c == 'ㅅ') return new ArrayList<>(Arrays.asList("ㅅ", "001000/", 1, "⠄"));
        if (c == 'ㅇ') return new ArrayList<>(Arrays.asList("ㅇ", "011011/", 1, "⠶"));
        if (c == 'ㅈ') return new ArrayList<>(Arrays.asList("ㅈ", "101000/", 1, "⠅"));
        if (c == 'ㅊ') return new ArrayList<>(Arrays.asList("ㅊ", "011000/", 1, "⠆"));
        if (c == 'ㅋ') return new ArrayList<>(Arrays.asList("ㅋ", "011010/", 1, "⠖"));
        if (c == 'ㅌ') return new ArrayList<>(Arrays.asList("ㅌ", "011001/", 1, "⠦"));
        if (c == 'ㅍ') return new ArrayList<>(Arrays.asList("ㅍ", "010011/", 1, "⠲"));
        if (c == 'ㅎ') return new ArrayList<>(Arrays.asList("ㅎ", "001011/", 1, "⠴"));
        if (c == 'ㄲ') return new ArrayList<>(Arrays.asList("ㄲ", "000001/100000/", 2, "⠠⠁"));
        if (c == 'ㅆ') return new ArrayList<>(Arrays.asList("ㅆ", "001100/", 1, "⠌"));
        if (c == 'ㄳ') return new ArrayList<>(Arrays.asList("ㄳ", "100000/001000/", 2, "⠁⠄"));
        if (c == 'ㄵ') return new ArrayList<>(Arrays.asList("ㄵ", "010010/101000/", 2, "⠒⠅"));
        if (c == 'ㄶ') return new ArrayList<>(Arrays.asList("ㄶ", "010010/001011/", 2, "⠒⠴"));
        if (c == 'ㄺ') return new ArrayList<>(Arrays.asList("ㄺ", "010000/100000/", 2, "⠂⠁"));
        if (c == 'ㄻ') return new ArrayList<>(Arrays.asList("ㄻ", "010000/010001/", 2, "⠂⠢"));
        if (c == 'ㄼ') return new ArrayList<>(Arrays.asList("ㄼ", "010000/110000/", 2, "⠂⠃"));
        if (c == 'ㄽ') return new ArrayList<>(Arrays.asList("ㄽ", "010000/001000/", 2, "⠂⠄"));
        if (c == 'ㄾ') return new ArrayList<>(Arrays.asList("ㄾ", "010000/011001/", 2, "⠂⠦"));
        if (c == 'ㄿ') return new ArrayList<>(Arrays.asList("ㄿ", "010000/010011/", 2, "⠂⠲"));
        if (c == 'ㅀ') return new ArrayList<>(Arrays.asList("ㅀ", "010000/001011/", 2, "⠂⠴"));
        if (c == 'ㅄ') return new ArrayList<>(Arrays.asList("ㅄ", "110000/001000/", 2, "⠃⠄"));
        return new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
    }

    static List no_han_H2b(char c) {
        if (c == 'a') return new ArrayList<>(Arrays.asList("a", "001011/100000/", 2, "⠴⠁"));
        if (c == 'A') return new ArrayList<>(Arrays.asList("A", "001011/100000/", 2, "⠴⠁"));

        if (c == 'b') return new ArrayList<>(Arrays.asList("b", "001011/110000/", 2, "⠴⠃"));
        if (c == 'B') return new ArrayList<>(Arrays.asList("B", "001011/110000/", 2, "⠴⠃"));

        if (c == 'c') return new ArrayList<>(Arrays.asList("c", "001011/100100/", 2, "⠴⠉"));
        if (c == 'C') return new ArrayList<>(Arrays.asList("C", "001011/100100/", 2, "⠴⠉"));

        if (c == 'd') return new ArrayList<>(Arrays.asList("d", "001011/100110/", 2, "⠴⠙"));
        if (c == 'D') return new ArrayList<>(Arrays.asList("D", "001011/100110/", 2, "⠴⠙"));

        if (c == 'e') return new ArrayList<>(Arrays.asList("e", "001011/100010/", 2, "⠴⠑"));
        if (c == 'E') return new ArrayList<>(Arrays.asList("E", "001011/100010/", 2, "⠴⠑"));

        if (c == 'f') return new ArrayList<>(Arrays.asList("f", "001011/110100/", 2, "⠴⠋"));
        if (c == 'F') return new ArrayList<>(Arrays.asList("F", "001011/110100/", 2, "⠴⠋"));

        if (c == 'g') return new ArrayList<>(Arrays.asList("g", "001011/110110/", 2, "⠴⠛"));
        if (c == 'G') return new ArrayList<>(Arrays.asList("G", "001011/110110/", 2, "⠴⠛"));

        if (c == 'h') return new ArrayList<>(Arrays.asList("h", "001011/110010/", 2, "⠴⠓"));
        if (c == 'H') return new ArrayList<>(Arrays.asList("H", "001011/110010/", 2, "⠴⠓"));

        if (c == 'i') return new ArrayList<>(Arrays.asList("i", "001011/010100/", 2, "⠴⠊"));
        if (c == 'I') return new ArrayList<>(Arrays.asList("I", "001011/010100/", 2, "⠴⠊"));

        if (c == 'j') return new ArrayList<>(Arrays.asList("j", "001011/010110/", 2, "⠴⠚"));
        if (c == 'J') return new ArrayList<>(Arrays.asList("J", "001011/010110/", 2, "⠴⠚"));

        if (c == 'k') return new ArrayList<>(Arrays.asList("k", "001011/101000/", 2, "⠴⠅"));
        if (c == 'K') return new ArrayList<>(Arrays.asList("K", "001011/101000/", 2, "⠴⠅"));

        if (c == 'l') return new ArrayList<>(Arrays.asList("l", "001011/111000/", 2, "⠴⠇"));
        if (c == 'L') return new ArrayList<>(Arrays.asList("L", "001011/111000/", 2, "⠴⠇"));

        if (c == 'm') return new ArrayList<>(Arrays.asList("m", "001011/101100/", 2, "⠴⠍"));
        if (c == 'M') return new ArrayList<>(Arrays.asList("M", "001011/101100/", 2, "⠴⠍"));

        if (c == 'n') return new ArrayList<>(Arrays.asList("n", "001011/101110/", 2, "⠴⠝"));
        if (c == 'N') return new ArrayList<>(Arrays.asList("N", "001011/101110/", 2, "⠴⠝"));

        if (c == 'o') return new ArrayList<>(Arrays.asList("o", "001011/101010/", 2, "⠴⠕"));
        if (c == 'O') return new ArrayList<>(Arrays.asList("O", "001011/101010/", 2, "⠴⠕"));

        if (c == 'p') return new ArrayList<>(Arrays.asList("p", "001011/111100/", 2, "⠴⠏"));
        if (c == 'P') return new ArrayList<>(Arrays.asList("P", "001011/111100/", 2, "⠴⠏"));

        if (c == 'q') return new ArrayList<>(Arrays.asList("q", "001011/111110/", 2, "⠴⠟"));
        if (c == 'Q') return new ArrayList<>(Arrays.asList("Q", "001011/111110/", 2, "⠴⠟"));

        if (c == 'r') return new ArrayList<>(Arrays.asList("r", "001011/111010/", 2, "⠴⠗"));
        if (c == 'R') return new ArrayList<>(Arrays.asList("R", "001011/111010/", 2, "⠴⠗"));

        if (c == 's') return new ArrayList<>(Arrays.asList("s", "001011/011100/", 2, "⠴⠎"));
        if (c == 'S') return new ArrayList<>(Arrays.asList("S", "001011/011100/", 2, "⠴⠎"));

        if (c == 't') return new ArrayList<>(Arrays.asList("t", "001011/011110/", 2, "⠴⠞"));
        if (c == 'T') return new ArrayList<>(Arrays.asList("T", "001011/011110/", 2, "⠴⠞"));

        if (c == 'u') return new ArrayList<>(Arrays.asList("u", "001011/101001/", 2, "⠴⠥"));
        if (c == 'U') return new ArrayList<>(Arrays.asList("U", "001011/101001/", 2, "⠴⠥"));

        if (c == 'v') return new ArrayList<>(Arrays.asList("v", "001011/111001/", 2, "⠴⠧"));
        if (c == 'V') return new ArrayList<>(Arrays.asList("V", "001011/111001/", 2, "⠴⠧"));

        if (c == 'w') return new ArrayList<>(Arrays.asList("w", "001011/010111/", 2, "⠴⠺"));
        if (c == 'W') return new ArrayList<>(Arrays.asList("W", "001011/010111/", 2, "⠴⠺"));

        if (c == 'x') return new ArrayList<>(Arrays.asList("x", "001011/101101/", 2, "⠴⠭"));
        if (c == 'X') return new ArrayList<>(Arrays.asList("X", "001011/101101/", 2, "⠴⠭"));

        if (c == 'y') return new ArrayList<>(Arrays.asList("y", "001011/101111/", 2, "⠴⠽"));
        if (c == 'Y') return new ArrayList<>(Arrays.asList("Y", "001011/101111/", 2, "⠴⠽"));

        if (c == 'z') return new ArrayList<>(Arrays.asList("z", "001011/101011/", 2, "⠴⠵"));
        if (c == 'Z') return new ArrayList<>(Arrays.asList("Z", "001011/101011/", 2, "⠴⠵"));

        if (c == '1') return new ArrayList<>(Arrays.asList("1", "001111/100000/", 2, "⠼⠁"));
        if (c == '2') return new ArrayList<>(Arrays.asList("2", "001111/110000/", 2, "⠼⠃"));
        if (c == '3') return new ArrayList<>(Arrays.asList("3", "001111/100100/", 2, "⠼⠉"));
        if (c == '4') return new ArrayList<>(Arrays.asList("4", "001111/100110/", 2, "⠼⠙"));
        if (c == '5') return new ArrayList<>(Arrays.asList("5", "001111/100010/", 2, "⠼⠑"));
        if (c == '6') return new ArrayList<>(Arrays.asList("6", "001111/110100/", 2, "⠼⠋"));
        if (c == '7') return new ArrayList<>(Arrays.asList("7", "001111/110110/", 2, "⠼⠛"));
        if (c == '8') return new ArrayList<>(Arrays.asList("8", "001111/110010/", 2, "⠼⠓"));
        if (c == '9') return new ArrayList<>(Arrays.asList("9", "001111/010100/", 2, "⠼⠊"));
        if (c == '0') return new ArrayList<>(Arrays.asList("0", "001111/010110/", 2, "⠼⠚"));
        if (c == ',') return new ArrayList<>(Arrays.asList(",", "010000/", 1, "⠂"));
        if (c == '.') return new ArrayList<>(Arrays.asList(".", "010011/", 1, "⠄"));
        if (c == '-') return new ArrayList<>(Arrays.asList("-", "010010/", 1, "⠤"));
        if (c == '?') return new ArrayList<>(Arrays.asList("?", "011001/", 1, "⠦"));
        if (c == '_') return new ArrayList<>(Arrays.asList("_", "001001/", 1, "⠤"));
        if (c == '!') return new ArrayList<>(Arrays.asList("!", "011010/", 1, "⠖"));
        if (c == ':') return new ArrayList<>(Arrays.asList(":", "000010/010000/", 2, "⠐⠂"));
        if (c == ';') return new ArrayList<>(Arrays.asList(";", "000011/011000/", 2, "⠰⠆"));
        return new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
    }

    static List abbr_H2b(String c) {
        if (c.equals("가")) return new ArrayList<>(Arrays.asList("가", "110101/", 1, "⠫"));
        if (c.equals("나")) return new ArrayList<>(Arrays.asList("나", "100100/", 1, "⠉"));
        if (c.equals("다")) return new ArrayList<>(Arrays.asList("다", "010100/", 1, "⠊"));
        if (c.equals("마")) return new ArrayList<>(Arrays.asList("마", "100010/", 1, "⠑"));
        if (c.equals("바")) return new ArrayList<>(Arrays.asList("바", "000110/", 1, "⠘"));
        if (c.equals("사")) return new ArrayList<>(Arrays.asList("사", "111000/", 1, "⠇"));
        if (c.equals("자")) return new ArrayList<>(Arrays.asList("자", "000101/", 1, "⠨"));
        if (c.equals("카")) return new ArrayList<>(Arrays.asList("카", "110100/", 1, "⠋"));
        if (c.equals("타")) return new ArrayList<>(Arrays.asList("타", "110010/", 1, "⠓"));
        if (c.equals("파")) return new ArrayList<>(Arrays.asList("파", "100110/", 1, "⠙"));
        if (c.equals("하")) return new ArrayList<>(Arrays.asList("하", "010110/", 1, "⠚"));
        if (c.equals("것")) return new ArrayList<>(Arrays.asList("것", "000111/011100/", 2, "⠸⠎"));
        if (c.equals("억")) return new ArrayList<>(Arrays.asList("억", "100111/", 1, "⠹"));
        if (c.equals("언")) return new ArrayList<>(Arrays.asList("언", "011111/", 1, "⠾"));
        if (c.equals("얼")) return new ArrayList<>(Arrays.asList("얼", "011110/", 1, "⠞"));
        if (c.equals("연")) return new ArrayList<>(Arrays.asList("연", "100001/", 1, "⠡"));
        if (c.equals("열")) return new ArrayList<>(Arrays.asList("열", "110011/", 1, "⠳"));
        if (c.equals("영")) return new ArrayList<>(Arrays.asList("영", "110111/", 1, "⠻"));
        if (c.equals("옥")) return new ArrayList<>(Arrays.asList("옥", "101101/", 1, "⠭"));
        if (c.equals("온")) return new ArrayList<>(Arrays.asList("온", "111011/", 1, "⠷"));
        if (c.equals("옹")) return new ArrayList<>(Arrays.asList("옹", "111111/", 1, "⠿"));
        if (c.equals("운")) return new ArrayList<>(Arrays.asList("운", "110110/", 1, "⠛"));
        if (c.equals("울")) return new ArrayList<>(Arrays.asList("울", "111101/", 1, "⠯"));
        if (c.equals("은")) return new ArrayList<>(Arrays.asList("은", "101011/", 1, "⠵"));
        if (c.equals("을")) return new ArrayList<>(Arrays.asList("을", "011101/", 1, "⠮"));
        if (c.equals("인")) return new ArrayList<>(Arrays.asList("인", "111110/", 1, "⠟"));
        if (c.equals("그래서")) return new ArrayList<>(Arrays.asList("그래서", "100000/011100/", 2, "⠁⠎"));
        if (c.equals("그러면")) return new ArrayList<>(Arrays.asList("그러면", "100000/010010/", 2, "⠁⠒"));
        if (c.equals("그러나")) return new ArrayList<>(Arrays.asList("그러나", "100000/100100/", 2, "⠁⠉"));
        if (c.equals("그러므로")) return new ArrayList<>(Arrays.asList("그러므로", "100000/010001/", 2, "⠁⠢"));
        if (c.equals("그런데")) return new ArrayList<>(Arrays.asList("그런데", "100000/101110/", 2, "⠁⠝"));
        if (c.equals("그리고")) return new ArrayList<>(Arrays.asList("그리고", "100000/101001/", 2, "⠁⠥"));
        if (c.equals("그리하여")) return new ArrayList<>(Arrays.asList("그리하여", "100000/100011/", 2, "⠁⠱"));
        return new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));
    }

    // 자소 분리 함수
    static String splitJaso(String s) {
        int a, b, c; // 자소 버퍼: 초성/중성/종성 순
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 0xAC00 && ch <= 0xD7A3) { // "AC00:가" ~ "D7A3:힣" 에 속한 글자면 분해
                c = ch - 0xAC00;
                a = c / (21 * 28);
                c = c % (21 * 28);
                b = c / 28;
                c = c % 28;
                result.append(ChoSung[a]).append(JwungSung[b]);
                if (c != 0)
                    result.append(JongSung[c]); // c가 0이 아니면, 즉 받침이 있으면

            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    static boolean abbr_Condition(String c) {
        String[] abbr = new String[]{"가", "나", "다", "마", "바", "사", "자", "카", "타", "파", "하",
                "것", "억", "언", "얼", "연", "영", "옥", "온", "옹", "운", "울", "을", "인",
                "그래서", "그러면", "그러나", "그러므로", "그런데", "그리고", "그리하여"};
        for (int i = 0; i < abbr.length; i++) {
            if (c.equals(abbr[i]))
                return true;
        }

        return false;
    }

    static boolean getType(String word) {
        return Pattern.matches("^[가-힣]*$", word);
    }


    static ArrayList<List> convert(String str){
        String[] word;
        word = str.split(" ");
        ArrayList space_braille = new ArrayList<>(Arrays.asList("\\", "000000/", 1, "ㆍ"));

        ArrayList<List> braille_list = new ArrayList();
        List temp;

        for (int i = 0; i < word.length; i++) {
            String voca = word[i]; // 단어 저장
//          Log.i("약어 단어 O - ", voca);
            if (H2b.abbr_Condition(voca)) {
                braille_list.add(H2b.abbr_H2b(voca));
            }
            else {
//              약어 단어 X
                for (int j = 0; j < voca.length(); j++) {
                    String letter = String.valueOf(voca.charAt(j)); // 글자 저장
//                  약어 글자 O
                    if (H2b.abbr_Condition(letter)) {
//                                Log.i("약어 글자 O - ", letter);
                        braille_list.add(H2b.abbr_H2b(letter));
                    }
//                  약어 글자 X
                    else {
                        if (H2b.getType(letter)) {
//                                    Log.i("한글 글자 O - ", letter);
                            if (H2b.splitJaso(letter).length() == 3) {
//                                        Log.i("종성O - ", letter);

//                              글자 형태소로 나누기
                                String element = H2b.splitJaso(letter);
//                              초성이 o 면 스킵 아니면 저장
                                if(element.charAt(0) != 'o'){
                                    temp = H2b.cho_H2b(element.charAt(0));
                                    braille_list.add(temp);
                                }
//                              중성 종성 저장
                                temp = H2b.joong_H2b(element.charAt(1));
                                braille_list.add(temp);

                                temp = H2b.jong_H2b(element.charAt(2));
                                braille_list.add(temp);
                            } else {
//                                        Log.i("종성X - ", letter)
//                              글자 형태소로 나누기
                                String element = H2b.splitJaso(letter);
//                              초성이 o 면 스킵 아니면 저장
                                if(element.charAt(0) != 'o'){
                                    temp = H2b.cho_H2b(element.charAt(0));
                                    braille_list.add(temp);
                                }
//                              중성 저장
                                temp = H2b.joong_H2b(element.charAt(1));
                                braille_list.add(temp);
                            }
                        } else {
//                          Log.i("한글X - ", letter);
//                          알파벳, 숫자, 특수문자 저장
                            temp = H2b.no_han_H2b(letter.charAt(0));
                            braille_list.add(temp);
                        }
                    }
                }
            }
//          단어 사이 공백 추가
            braille_list.add(space_braille);
        }
        return braille_list;
    }
}
