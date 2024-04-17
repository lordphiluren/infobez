package ru.sushchenko.infobez.service.crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlayfairMatrix {
    public static char[][] createTable(String key) {
        key = key.toUpperCase();
        List<Character> newAlphabet = new ArrayList<>();
        for (char c : (key + "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ").toCharArray()) {
            if (!newAlphabet.contains(c))
                newAlphabet.add(c);
        }

        char[][] table = new char[4][8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                table[i][j] = newAlphabet.get(index++);
            }
        }
        return table;
    }

    public static List<String> divide(String message) {
        message = message.toUpperCase().replaceAll("\\s+", "").replaceAll("[^А-Я]", "");
        if (message.length() % 2 != 0)
            message += "Я";

        List<String> divided = new ArrayList<>();
        for (int i = 0; i < message.length(); i += 2) {
            String pair = message.substring(i, i + 2);
            if (pair.charAt(0) == pair.charAt(1)) {
                pair = pair.substring(0, 1) + "Я" + pair.substring(1);
            }
            divided.add(pair);
        }
        return divided;
    }

    public static List<String> encrypt(char[][] table, List<String> dividedMessage) {
        List<String> pairs = new ArrayList<>();

        for (String chars : dividedMessage) {
            int[] pos1 = findElement(table, chars.charAt(0));
            int[] pos2 = findElement(table, chars.charAt(1));

            if (pos1[0] != pos2[0] && pos1[1] != pos2[1]) {
                pairs.add(String.valueOf(table[pos1[0]][pos2[1]]) + table[pos2[0]][pos1[1]]);
            } else if (pos1[1] == pos2[1]) {
                pairs.add(String.valueOf(table[(pos2[0] - 1 + 4) % 4][pos2[1]]) +
                        table[(pos1[0] - 1 + 4) % 4][pos1[1]]);
            } else {
                pairs.add(String.valueOf(table[pos1[0]][(pos1[1] + 1) % 8]) +
                        table[pos2[0]][(pos2[1] + 1) % 8]);
            }
        }
        return pairs;
    }

    public static int[] findElement(char[][] matrix, char value) {
        int[] pos = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == value) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }
    public static HashMap<String, Object> playfair(String key, String plainText) {
        char[][] table = createTable(key.toUpperCase().replaceAll("\\s+", ""));
        List<String> dividedMessage = divide(plainText);
        List<String> encryptedMessage = encrypt(table, dividedMessage);
        HashMap<String, Object> result = new HashMap<>();
        result.put("Сообщение: ", encryptedMessage);
        result.put("Таблица: ", table);
        return result;
    }
}
