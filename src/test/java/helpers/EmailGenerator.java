package helpers;

public class EmailGenerator {

 /*   public static void main(String[] args) {
        System.out.println("Generated mail : "+generateEmail(10,5,3));
    }*/

    /**
     * Метод для генерации случайных адресов электронной почты.
     * @param a должен быть больше 0
     * @param b должен быть больше 0
     * @param c должен быть больше 0
     * @return String email
     *  три параметра a, b и c, представляющих количество символов в локальной части почтового адреса, доменной части и расширении соответственно. a@b.c
     * Все циклы for написаны для генерации случайных символов, добавляя их к объекту StringBuilder, который затем преобразуется в строку и возвращается в качестве сгенерированного почтового адреса.
     */
    public static String generateEmail(int a, int b, int c){
        if(a <=0 || b <=0 || c <=0){
            throw new IllegalArgumentException("Wrong parametr: "
                     + a + " OR "+ b+" OR "+c+ " Length must be positive.");
        }
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++ ){
            email.append(randomChar());
        }
        email.append("@");
        for (int i = 0; i < b; i++ ){
            email.append(randomChar());
        }
        email.append(".");
        for (int i = 0; i < c; i++ ){
            email.append(randomChar());
        }
        return email.toString();
    }
 // метод randomChar возвращает случайный символ в диапазоне от 'a' до 'z'.
    private static  char randomChar(){
        return (char) ('a'+Math.random() * ('z' - 'a'));
    }
}
