package helpers;

import java.util.Random;

public class PasswordStringGenerator {

    public static String generateString() {
    StringBuilder stringBuilder = new StringBuilder();


    // Генерируем символы в верхнем регистре
    for (int i = 0; i < 4; i++) {
        char upperCaseChar = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
        stringBuilder.append(upperCaseChar);
    }

    // Генерируем символы в нижнем регистре
    for (int i = 0; i < 5; i++) {
        char lowerCaseChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
        stringBuilder.append(lowerCaseChar);
    }

    // Генерируем цифры
    Random random = new Random();
    for (int i = 0; i < 3; i++) {
        int digit = random.nextInt(10);
        stringBuilder.append(digit);
    }

    // Генерируем один или более спецсимволов ([]!$_)
    String specialChars = "~$_-";
    int specialCharsCount = 1 + random.nextInt(3); // Генерируем от 1 до 3 спецсимволов
    for (int i = 0; i < specialCharsCount; i++) {
        int index = random.nextInt(specialChars.length());
        char specialChar = specialChars.charAt(index);
        stringBuilder.append(specialChar);
    }

    return stringBuilder.toString();
}

    public static void main(String[] args) {
        System.out.println("RESULT: "+Math.random());
    }
}