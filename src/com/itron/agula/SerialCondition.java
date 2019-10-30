package com.itron.agula;

import java.util.HashMap;
import java.util.Map;

public class SerialCondition {
    private static final Map<String, String> letters = new HashMap<String, String>();
    static {
        letters.put("А", "A");
        letters.put("В", "B");
        letters.put("Е", "E");
        letters.put("И", "U");
        letters.put("К", "K");
        letters.put("М", "M");
        letters.put("Н", "H");
        letters.put("О", "O");
        letters.put("Р", "P");
        letters.put("С", "C");
        letters.put("Т", "T");
        letters.put("У", "Y");
        letters.put("Х", "X");
        letters.put("'", "");
        letters.put("\"", "");
        letters.put("\\", "");
        letters.put("/", "");
        letters.put(",", "");
    }

    public static String toCondition(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i<text.length(); i++) {
            String l = text.substring(i, i+1);
            if (letters.containsKey(l)) {
                sb.append(letters.get(l));
            }
            else {
                sb.append(l);
            }
        }
        return sb.toString();
    }
}
