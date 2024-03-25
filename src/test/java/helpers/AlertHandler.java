package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandler {
    WebDriver driver;
    public AlertHandler(WebDriver driver){this.driver=driver;}

    /**
     * Это объявление статического метода handleAlert(). Метод принимает два параметра: объект Alert,
     * представляющий всплывающее окно, и строку expectedText, содержащую ожидаемый текст в всплывающем окне.
     * Метод возвращает логическое значение true, если текст в всплывающем окне содержит ожидаемый текст,
     * в противном случае возвращает false.
     * @param alert
     * @param expectedTExt
     * @return boolean
     */
    public static  boolean handleAlert(Alert alert, String expectedTExt){
            if (alert !=null){ // Эта строка проверяет, что объект Alert не равен null, то есть всплывающее окно существует.
                String alertText = alert.getText(); // Получаем текст из этого окна.
                System.out.println("ALERT_TEXT "+alertText);
                alert.accept();  // Принимаем всплывающее окно, нажимая кнопку "OK"
                return alertText.contains(expectedTExt); // Возвращает true, если текст в всплывающем окне содержит ожидаемый текст (expectedText),
                // в противном случае возвращает false.
                    }
            return false;

    }
}
