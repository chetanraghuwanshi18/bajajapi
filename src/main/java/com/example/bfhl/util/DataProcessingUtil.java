package com.example.bfhl.util;

import java.util.List;

public final class DataProcessingUtil {

    private DataProcessingUtil() {}

    /**
     * Returns true if the entire string represents an integer number (may be negative).
     */
    public static boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        int start = 0;
        if (s.charAt(0) == '-') {
            if (s.length() == 1) return false;
            start = 1;
        }
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Returns true if the entire string consists only of alphabetic characters.
     */
    public static boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Returns true if the entire string consists only of non-alphanumeric characters.
     */
    public static boolean isSpecialCharacter(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) return false;
        }
        return true;
    }

    /**
     * Builds the concat_string:
     * 1. Extract all alphabetic characters from all tokens.
     * 2. Concatenate them.
     * 3. Reverse.
     * 4. Apply alternating caps (first char uppercase, second lowercase, ...).
     */
    public static String buildConcatString(List<String> data) {
        if (data == null || data.isEmpty()) return "";

        StringBuilder allAlpha = new StringBuilder();
        for (String token : data) {
            if (token == null) continue;
            for (char c : token.toCharArray()) {
                if (Character.isLetter(c)) {
                    allAlpha.append(c);
                }
            }
        }

        String reversed = allAlpha.reverse().toString();
        return applyAlternatingCaps(reversed);
    }

    /**
     * Converts a string into alternating caps:
     * index 0 -> uppercase, index 1 -> lowercase, index 2 -> uppercase, ...
     */
    public static String applyAlternatingCaps(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }
}
