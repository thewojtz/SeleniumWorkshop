package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MenuPage;
import pages.ProfilePage;
import pages.TransferPage;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;
import static org.junit.Assert.assertEquals;
import static tests.MainTestData.*;

public class MainTest {
    private WebDriver driver;



    @Before
    public void setUp() {
        driver = initializeWebDriver();
        driver.get(getConfiguration().getSiteURL());
    }

    @Test
    public void sendTransferAndCheckIsCorrect() {

        MenuPage menuPage = new MenuPage(driver);
        TransferPage transferPage = menuPage.selectTransfersPage();
        transferPage.fillAccountNumber("123456789");
        //assertEquals("Account number is correct", "123456789", transferPage.getFilledAccountNumber());
        transferPage.fillTransferTitle("lololololo title");
        //assertEquals("Transfer title is correct","lololololo title",transferPage.getFilledTransferTitle());
        transferPage.fillTransferRecipient("lolololo recipient");
        //assertEquals("Transfer recipient is correct","lolololo recipient",transferPage.getFilledTransferRecipient());
        transferPage.fillTransferAmount("11111");
        transferPage.selectTransferType(TransferPage.TRANSFER_TYPE.FAST);
        transferPage.sendTransfer();
        assertEquals("Transfer amount is not correct",
                "1000",
                transferPage.getPendingTransferAmount());
    }

    @Test
    public void updateUserProfileDataAndCheckIfIsCorrect(){

        MenuPage menuPage = new MenuPage(driver);
        ProfilePage profilePage = menuPage.selectProfilePage();

        profilePage.updateName("nanananana");
        profilePage.updateCountry("Sweden");
        profilePage.updateCity("name of a city");
        profilePage.updatePostalCode("11111");
        profilePage.updateEmail("anemail@something.com");
        profilePage.updateStreet("street in a city");
        profilePage.updateAdditional("yeah why not");
        profilePage.selectGender(ProfilePage.GENDER_CHOICE.FEMALE);
        profilePage.toggleNotifications(false);
        profilePage.saveUser();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
