package model;

import java.io.*;
import java.util.Objects;

public class Contact implements Serializable {

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact(String name, String lastName, String phone, String email, String address, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return Objects.equals(getName(), contact.getName()) && Objects.equals(getLastName(),
                contact.getLastName()) && Objects.equals(getPhone(),
                contact.getPhone()) && Objects.equals(getEmail(),
                contact.getEmail()) && Objects.equals(getAddress(),
                contact.getAddress()) && Objects.equals(getDescription(), contact.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getPhone(), getEmail(), getAddress(), getDescription());
    }
    // сериализация объекта типа Contact в файл с помощью класса ObjectOutputStream.
    public  static void serializeContact(Contact contact, String fileName) throws IOException {
        // Объявление метода serializeContact, который принимает два параметра: объект типа Contact,
        // который мы хотим сериализовать, и строковое имя файла, в который мы хотим сохранить сериализованный объект.
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        // Создание объекта ObjectOutputStream, который используется для записи объектов Java в поток вывода.
        // Мы передаем ему поток вывода файла (FileOutputStream), указывая имя файла fileName,
        // который будет использоваться для сохранения данных.
        outputStream.writeObject(contact); // Метод writeObject сериализует объект и записывает его в поток.
        // После этого объект будет сохранен в файле, указанном в fileName.
    }
    public static Contact desiarializeContact(String fileName){
        try (
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));){
            return (Contact) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during contact desiarilization " );
            return null;
        }


    }
}
