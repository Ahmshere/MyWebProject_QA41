package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class WindowAndTabTests {
    public static void main(String[] args) throws InterruptedException {
       // switchTab();
       // sliderTest();

       // findRowByValue("Frank");
        // findRowByValueLambda("Frank");
        rightMouseClick();
    }
    public static void rightMouseClick() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        WebElement  element = driver.findElement(By.xpath("//a[contains(text(),'Testing')]"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();

        Thread.sleep(3000);
        driver.quit();


    }
@Test
    public static  void sliderTest() throws InterruptedException { // Указание throws InterruptedException означает, что метод может выбрасывать исключение InterruptedException.


       WebDriver driver = new FirefoxDriver(); // Создание экземпляра веб-драйвера Firefox. Этот драйвер будет использоваться для управления браузером.
        driver.get("https://demoqa.com/slider");
        driver.manage().window().maximize(); // Максимизация окна браузера для полноэкранного просмотра.
    WebElement slider = driver.findElement(By.xpath("//input[@type='range']")); // Нахождение элемента слайдера на веб-странице с помощью XPath.
    Actions action = new Actions(driver); // Создание объекта класса Actions, который используется для выполнения действий с мышью или клавиатурой на странице.
    action.dragAndDropBy(slider, 30, 0).build().perform(); // Выполнение действия перетаскивания ползунка слайдера на 30 пикселей вправо (горизонтальное перемещение). Затем метод perform() применяет это действие.
    Thread.sleep(3000);

/*
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        slider.sendKeys(Keys.ARROW_RIGHT); Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT); Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT); Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT); Thread.sleep(1000);
        slider.sendKeys(Keys.ARROW_RIGHT); Thread.sleep(1000);*/
        driver.quit(); // Завершение сеанса веб-драйвера. Этот шаг очищает ресурсы, освобождает браузер и завершает сеанс, что важно для правильной работы и завершения теста.
    }
    public static void switchTab(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();

        String mainWindowHandler = driver.getWindowHandle(); // Получает идентификатор текущего (главного) окна браузера и сохраняет его в переменной mainWindowHandler.
        WebElement newButton = driver.findElement(By.xpath("//button[@id='tabButton']")); //Находим кнопку
        newButton.click(); // и жмем на нее, чтобы открыть новую вкладку.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// Создает объект WebDriverWait, который будет ожидать на протяжении 5 секунд, чтобы выполнить следующую команду.
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Ожидает, пока количество открытых окон в браузере не станет равным 2.
        Set<String> allWindowHandles = driver.getWindowHandles();// Получает все идентификаторы окон, открытых в браузере, и сохраняет их в коллекции типа Set<String>.

        String newWindowHandler = ""; // Объявляет переменную newWindowHandler, которая будет использоваться для хранения идентификатора нового окна или вкладки.
        for (String windowHandle : allWindowHandles){ // Пробегаемся по всем идентификаторам окон, найденным на предыдущем шаге.
            if(!windowHandle.equals(mainWindowHandler)){ // Проверяем, является ли текущий идентификатор окна отличным от идентификатора главного окна, сохраненного на первом шаге.
                newWindowHandler = windowHandle;// Если идентификатор окна отличается от главного, сохраняем этот идентификатор в переменной newWindowHandler и завершаем цикл.
                break;
            }
        }
        driver.switchTo().window(newWindowHandler); // Переключаемся на новую вкладку, используя его идентификатор.
        WebElement newPageElement = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));//  пытаемся найти элемент на новой вкладке.
        newPageElement.click();
        driver.quit();

    }
    @Test
    public static void dragAndDropTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();

        WebElement elementToDrop = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement targetElement = driver.findElement(By.xpath("//div[@id='column-b']"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementToDrop, targetElement).build().perform();
        Thread.sleep(3000);
        driver.quit();

    }

    @Test
    public static void waitForAnElement(){
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement buttonStart = driver.findElement(By.xpath("//button"));
        buttonStart.click();


       // WebElement textElement = driver.findElement(By.xpath("//div[@id='finish']"));
       // textElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']")));
        textElement.click();

        driver.quit();

    }

    public  static  String findRowByValue(String valueToFind){

        WebDriver driver = new FirefoxDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/tables");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement tableElement = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.tagName("table")));
            List<WebElement> rows = tableElement.findElements(By.tagName("tr"));

            for (WebElement row : rows){
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells){
                    if(cell.getText().equals(valueToFind)){
                        System.out.println(row.getText());
                        return row.getText();
                    }
                }
            }return null;
        }finally {
            driver.quit();
        }
    }

// Lambda

    public  static  String findRowByValueLambda(String valueToFind){

        WebDriver driver = new FirefoxDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/tables");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement tableElement = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.tagName("table")));
            List<WebElement> rows = tableElement.findElements(By.tagName("tr"));

                Optional<WebElement> optionalRow = rows.stream()
                        .filter(row -> row.findElements(By.tagName("td"))
                                .stream().allMatch(cell -> cell.getText().equals(valueToFind))).findFirst();

            return optionalRow.map(WebElement::getText).orElse(null);

        }finally {
            driver.quit();
        }




    }







}
