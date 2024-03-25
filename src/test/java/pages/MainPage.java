package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){ // конструктор класса MainPage, который принимает объект WebDriver в качестве параметра.
        setDriver(driver); // Устанавливает переданный объект WebDriver в качестве драйвера для экземпляра класса MainPage
        driver.get("https://telranedu.web.app/"); // Переходит на указанный URL-адрес веб-страницы с помощью метода get
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this); /* Инициализирует элементы страницы с помощью PageFactory.
         *     Она ищет все аннотированные элементы @FindBy в классе MainPage и связывает их с реальными элементами веб-страницы.
         *     Используется AjaxElementLocatorFactory для ожидания элементов до их появления на странице в течение указанного времени
         */
    }

    /**
     * Возвращаем объект страницы в зависимости от переданного параметра
     * @param topMenuItem - параметр, который получаем из enum TopMenuItem
     * @return Любой тип, наследованый от BasePage
     * @param <T> Это обобщённый тип данных.
     */
    public static <T extends BasePage> T openTopMenu(String topMenuItem){ //  <T extends BasePage>: Это обобщённый тип данных. Он объявляет тип T,который является подтипом класса BasePage.
        WebElement menuItem = driver.findElement(By.xpath("//a[contains(text(),'"+topMenuItem+"')]"));
        menuItem.click();

        switch (topMenuItem){
            case "HOME":
                return (T) new HomePage(driver); // new HomePage(driver): Это оператор создания нового объекта класса HomePage. Здесь driver передается в качестве аргумента конструктору класса HomePage. пример использования обобщенного программирования в Java // (T): Это оператор приведения типа (type casting). В данном случае (T) означает, что мы приводим созданный объект к типу T.
            case "ABOUT":
                return (T) new AboutPage(driver);// тоже , что и с new HomePage(driver)
            case "LOGIN":
                return (T) new LoginPage(driver);// тоже , что и с new HomePage(driver)
            case "ADD" :
                return (T) new AddPage(driver);

            default: throw new IllegalArgumentException("Somethings wrong"+ topMenuItem);
        }

    }
}
