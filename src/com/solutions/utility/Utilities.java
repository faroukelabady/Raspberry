/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.utility;

/**
 *
 * @author farouk
 */
public class Utilities {

    private static final char[] _nibbleToHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHex(byte[] code) {
        StringBuilder result = new StringBuilder(2 * code.length);

        for (int i = 0; i < code.length; i++) {
            int b = code[i] & 0xFF;
            result.append(_nibbleToHex[b / 16]);
            result.append(_nibbleToHex[b % 16]);
        }

        return result.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean isSeek(String data) {

        if (data.contains("seek")) {
            return true;
        } else {
            return false;
        }

    }
}
