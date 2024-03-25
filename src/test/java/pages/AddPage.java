package pages;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPage extends  BasePage{

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement nameField;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastnameField;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement phoneField;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement addressField;
    @FindBy(xpath = "//input[@placeholder='description']")
    WebElement descriptionField;
    @FindBy(xpath = "//b[contains(text(),'Save')]")
    WebElement saveButton;

    public AddPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    public void fillFormAndSave(Contact contact){
        nameField.sendKeys(contact.getName());
        lastnameField.sendKeys(contact.getLastName());
        /**
         * Это условие проверяет длину телефонного номера контакта.
         * Если длина находится в диапазоне от 10 до 15 символов,
         * тогда номер телефона отправляется в соответствующее поле веб-формы.
         * Если длина не соответствует ожидаемому диапазону, выбрасывается исключение IllegalArgumentException.
         */
        if(contact.getPhone().length()>=10 && contact.getPhone().length()<=15){
            phoneField.sendKeys(contact.getPhone());
        }else{
            throw new IllegalArgumentException("The phone number issue...");
        }
        emailField.sendKeys(contact.getEmail());
        addressField.sendKeys(contact.getAddress());
        descriptionField.sendKeys(contact.getDescription());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //  Этот код использует объект WebDriverWait для ожидания, пока кнопка "Save" на веб-странице не станет видимой.
        //  Затем она сохраняется в переменную saveButton.
        WebElement saveButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//b[contains(text(),'Save')]")));
        saveButton.click();
    }

}
