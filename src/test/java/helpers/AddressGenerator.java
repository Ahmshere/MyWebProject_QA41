package helpers;

import java.util.Random;

public class AddressGenerator {

 /*   public static void main(String[] args) {
        System.out.println("ADDRESS : "+generateAddress());
    }*/

    private static final String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
    private static final String[] streets = {"Main", "Broadway", "First", "Second", "Third", "Fourth", "Fifth", "Park", "Oak", "Pine"};
    private static final String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
    // объект Random будет использоваться для генерации случайных чисел.
    private static final Random random = new Random();

    /**
     * Метод возвращает случайно сгенерированный адрес в формате строки.
     * @return String
     */
    public static String generateAddress(){
        // Выбирается случайный город из массива cities.
        String city = cities[random.nextInt(cities.length)];
        //Выбирается случайная улица из массива streets.
        String street = streets[random.nextInt(streets.length)];
        //Генерируется случайное число
        int number = random.nextInt(300);
        //Выбирается случайный штат из массива states.
        String state = states[random.nextInt(states.length)];
        // Формируется строка адреса из выбранных случайных значений.
        return city+" "+street+" "+number+" "+state;
    }
}
