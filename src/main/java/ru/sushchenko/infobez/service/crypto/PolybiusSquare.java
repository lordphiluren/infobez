package ru.sushchenko.infobez.service.crypto;

public class PolybiusSquare {
    private static final char[][] POLYBIUS_SQUARE = {
            {'а', 'б', 'в', 'г', 'д', 'е'},
            {'ж', 'з', 'и', 'й', 'к', 'л'},
            {'м', 'н', 'о', 'п', 'р', 'с'},
            {'т', 'у', 'ф', 'х', 'ц', 'ч'},
            {'ш', 'щ', 'ъ', 'ы', 'ь', 'э'},
            {'ю', 'я', ' ', ' ', ' ', ' '}
    };
    public static String encodeText(String plaintext) {
        plaintext = plaintext.toLowerCase().replaceAll("[^а-я]+", "");

        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (currentChar == 'й') {
                encodedText.append("24");
            } else {
                for (int row = 0; row < POLYBIUS_SQUARE.length; row++) {
                    for (int col = 0; col < POLYBIUS_SQUARE[row].length; col++) {
                        if (POLYBIUS_SQUARE[row][col] == currentChar) {
                            encodedText.append(row + 1).append(col + 1);
                        }
                    }
                }
            }
        }

        return encodedText.toString();
    }
}
