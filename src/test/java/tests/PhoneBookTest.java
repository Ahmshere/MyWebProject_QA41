package tests;

import config.BaseTest;
import helpers.*;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import model.Contact;
import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.IOException;

public class PhoneBookTest extends BaseTest {

    @Test(description = "The test checks the empty field warning declaration.", retryAnalyzer = RetryAnalizer.class)
    @Parameters("browser")
    public void registrationWithoutPassword(@Optional("chrome") String browser) throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");

        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Click by Login button");
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Click by Reg button");
        String expectedString = "Wrong";

      Alert alert= loginPage.fillEmailField("myemail@mail.com").clickByRegistartionButton();
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedString);
        Assert.assertTrue(isAlertHandled);
    }
    @Test(retryAnalyzer = RetryAnalizer.class)
    @Description("User already exist. Login and add contact.")
    public void loginOfAnExistingUserAddContact() throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Step 1");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Step 2");
        lpage.fillEmailField(PropertiesReader.getProperty("existingUserEmail"))
                .fillPasswordField(PropertiesReader.getProperty("existingUserPassword"))
                .clickByLoginButton();
        Allure.step("Step 3");
        MainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(10,5,3),
                AddressGenerator.generateAddress(),
                "new description");
        newContact.toString();
        addPage.fillFormAndSave(newContact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
        TakeScreen.takeScreenshot("screen");
        Thread.sleep(3000);
    }
    @Test
    @Description("Successful Registration")
    public void successfulRegistration(){
        Allure.description("Successful Registration test.");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Open LOGIN menu");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lpage.fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField(PasswordStringGenerator.generateString());
        Alert alert =  lpage.clickByRegistartionButton();
        if (alert==null){
            ContactsPage contactsPage = new ContactsPage(getDriver());
            Assert.assertTrue( contactsPage. isElementPersist(getDriver()
                    .findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));
        }else {
            TakeScreen.takeScreenshot("Successful Registration");}
    }
  //  @Test
    public void deleteContact() throws InterruptedException {
        Allure.description("User already exist. Delete contact by phone number!");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Step 1");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Step 2");
        lpage.fillEmailField(PropertiesReader.getProperty("existingUserEmail"))
                .fillPasswordField(PropertiesReader.getProperty("existingUserPassword"))
                .clickByLoginButton();
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertNotEquals(contactsPage.deleteContactByPhoneNumberOrName("2101225254138"),
                contactsPage.getContactsListSize(),"Contact lists are different");
    }

    @Test
    public void deleteContactApproachTwo() throws IOException {
        String filename = "contactDataFile.ser";
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lpage.fillEmailField(PropertiesReader.getProperty("existingUserEmail"))
                .fillPasswordField(PropertiesReader.getProperty("existingUserPassword"))
                .clickByLoginButton();
        MainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact(NameAndLastNameGenerator.generateName(),NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(10,5,3),
                AddressGenerator.generateAddress(), "Test description");
        addPage.fillFormAndSave(newContact);
        Contact.serializeContact(newContact, filename);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Contact deserContact = Contact.desiarializeContact(filename);
        Assert.assertNotEquals(contactsPage.deleteContactByPhoneNumberOrName(deserContact.getPhone()),
                contactsPage.getContactsListSize());
    }

    @Test
    @Description("Registration attempt test.")
    public void reRegistrationAttempt() {
        boolean res = false;
        Allure.description("Registration attempt test.");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Open LOGIN menu");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        User user = new User(EmailGenerator
                .generateEmail(7, 7, 3), PasswordStringGenerator.generateString());

        lpage.fillEmailField(user.getUserEmail())
                .fillPasswordField(user.getUserPassword());
        Alert alert = lpage.clickByRegistartionButton();
        if (alert == null) {
            ContactsPage contactsPage = new ContactsPage(getDriver());
            lpage = contactsPage.clickBySignOutButton();
            Alert alert1 = lpage.fillEmailField(user.getUserEmail())
                    .fillPasswordField(user.getUserPassword()).clickByRegistartionButton();
            if (alert1 != null) {
                res = AlertHandler.handleAlert(alert1, "exist");
            }
        } else {
            System.out.println("reRegistrationAttempt");
        }
        Assert.assertTrue(res, "Registration attempt failed");
    }


}
