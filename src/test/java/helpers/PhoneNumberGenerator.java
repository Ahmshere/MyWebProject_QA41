package helpers;

import java.util.Random;

public class PhoneNumberGenerator {
  /*  public static void main(String[] args) {
        System.out.println("NUMBER : "+generatePhoneNumber());
    }*/
    // две константы MIN_LENGTH и MAX_LENGTH представляют минимальную и максимальную длину телефонного номера соответственно.
    private static  final int MIN_LENGTH = 10;
    private static  final int MAX_LENGTH = 15;

// метод generatePhoneNumber генерирует случайный телефонный номер.
    // Длина телефонного номера выбирается случайным образом в диапазоне от MIN_LENGTH до MAX_LENGTH.
    public static String generatePhoneNumber(){
        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH - MIN_LENGTH-1)+MIN_LENGTH;
        StringBuilder phoneNumber = new StringBuilder();
        for (int i=0; i < length; i++){
            if(i == 0){
                /**
                 * Этот метод nextInt(int bound) из класса Random генерирует случайное целое число в диапазоне
                 * от 0 (включительно) до bound - 1. В данном случае bound равно 7, поэтому этот вызов
                 * random.nextInt(7) может вернуть числа от 0 до 6.
                 * + 2: После генерации случайного числа от 0 до 6, к нему прибавляется 2.
                 * Это смещение приводит к изменению диапазона с 0-6 на 2-8.
                 * Таким образом, результат этой части выражения будет в диапазоне от 2 до 8.
                 */
                phoneNumber.append(random.nextInt(7)+2);
                // используется для генерации случайной цифры от 0 до 9
                // и добавления её к объекту StringBuilder под названием phoneNumber.
            }else {phoneNumber.append(random.nextInt(10));
                /**
                 * random.nextInt(10): Этот метод nextInt(int bound) из класса Random
                 * генерирует случайное целое число в диапазоне от 0 (включительно) до bound - 1.
                 * В данном случае bound равно 10, поэтому этот вызов random.nextInt(10) может вернуть числа от 0 до 9.
                 */
            }
        }
        return phoneNumber.toString();
    }

}
