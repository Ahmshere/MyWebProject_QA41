package helpers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {
    public static void main(String[] args) {
        writeProperties("myKey4","myValue", false);
    }

    private static final  String PROPERTIES_FILE_PATH = "src/test/resources/data.properties";
// Метод используется для записи пары ключ-значение в файл свойств
// Метод принимает три параметра: key - ключ, value - значение, которое необходимо записать, и cleanFile - флаг,
// указывающий, нужно ли очистить файл перед записью нового значения.
    public static  void writeProperties(String key, String value, boolean cleanFile){

        Properties properties = new Properties(); // Создает объект Properties, который используется для работы с файлом свойств
        FileInputStream fileInputStream = null; // переменная fileInputStream будет использоваться для чтения из файла.
        FileOutputStream fileOutputStream = null; // для записи в файл
try {
    if (!Files.exists(Paths.get(PROPERTIES_FILE_PATH))) { // Проверяет, существует ли файл по указанному пути PROPERTIES_FILE_PATH. Если файл не существует, создается новый файл.
        Files.createFile(Paths.get(PROPERTIES_FILE_PATH));
    }
    fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH); // Создает поток ввода из файла, который будет использоваться для загрузки существующих свойств из файла.

    properties.load(fileInputStream); // Загружает свойства из файла
    if (cleanFile) { // Если cleanFile установлен в true, очищает свойства из объекта Properties, что означает удаление всех ранее записанных свойств из файла.
        properties.clear();
    }
    properties.setProperty(key, value); // Устанавливаем свойство с указанным ключом и значением.
    fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH); //  поток вывода в файл для записи свойств.
    properties.store(fileOutputStream, "My comment"); // Сохраняет свойства в файл. Второй параметр - это комментарий, который будет добавлен в файл.
}
catch (IOException e){e.printStackTrace();}
finally {
    try {
        if (fileInputStream != null){
            fileInputStream.close();
        }
        if(fileOutputStream != null){
            fileOutputStream.close();
        }

    }catch (IOException exception){exception.printStackTrace();}
}

    }

}
