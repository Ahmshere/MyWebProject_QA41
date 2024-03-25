package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static final  String PROPERTIES_FILE_PATH = "src/test/resources/resources.properties";

    public static  String getProperty(String key)  {

        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)){
            properties.load(fis);
            return properties.getProperty(key);
        }catch (IOException exception){
            exception.printStackTrace();
            return null;
        }

    }
}
