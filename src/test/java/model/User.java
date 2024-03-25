package model;

import java.io.*;
import java.util.Objects;

public class User implements Serializable {
   private String userEmail;
    private String userPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUserEmail(), user.getUserEmail()) && Objects.equals(getUserPassword(), user.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getUserPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail= '" + userEmail + '\'' +
                ", userPassword= '" + userPassword + '\'' +
                '}';
    }

    public  static void serializeContact(User user, String fileName) throws IOException {
        // Объявление метода serializeContact, который принимает два параметра: объект типа Contact,
        // который мы хотим сериализовать, и строковое имя файла, в который мы хотим сохранить сериализованный объект.
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        // Создание объекта ObjectOutputStream, который используется для записи объектов Java в поток вывода.
        // Мы передаем ему поток вывода файла (FileOutputStream), указывая имя файла fileName,
        // который будет использоваться для сохранения данных.
        outputStream.writeObject(user); // Метод writeObject сериализует объект и записывает его в поток.
        // После этого объект будет сохранен в файле, указанном в fileName.
    }
    public static User desiarializeContact(String fileName){
        try (
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));){
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during contact desiarilization " );
            return null;
        }


    }

    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
