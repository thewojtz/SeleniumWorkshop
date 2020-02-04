package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MenuPage;
import pages.ProfilePage;
import pages.TransferPage;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;
import static org.junit.Assert.assertEquals;

public class ProfileTest implements  ProfileTestData{

    private WebDriver driver;


    @Before
    public void setUp() {
        driver = initializeWebDriver();
        //driver.manage().
        driver.get(getConfiguration().getSiteURL());
    }

    @Test
    public void updateUserProfileDataAndCheckIfIsCorrect(){

        MenuPage menuPage = new MenuPage(driver);
        ProfilePage profilePage = menuPage.selectProfilePage();

        profilePage.updateName(NAME)
            .updateCountry(SELECTED_COUNTRY)
            .updateCity(CITY_NAME)
            .updatePostalCode(POSTAL_CODE)
            .updateEmail(EMAIL)
            .updateStreet(STREET_NAME)
            .updateAdditional(ADDITIONAL)
            .selectGender(ProfilePage.GENDER_CHOICE.FEMALE)
            .toggleNotifications(false)
            .saveUser();

        assertEquals("Name is correct", NAME, profilePage.getUserName());
        assertEquals("Country is correct", SELECTED_COUNTRY, profilePage.getUserCountry());
        assertEquals("City is correct", CITY_NAME, profilePage.getUserCity());
        assertEquals("Postal code is correct", POSTAL_CODE, profilePage.getUserPostalCode());
        assertEquals("Email is correct", EMAIL, profilePage.getUserEmail());
        assertEquals("Street name is correct", STREET_NAME, profilePage.getUserStreet());
        assertEquals("Additional is correct", ADDITIONAL, profilePage.getUserNote());
        assertEquals("Gender is correct", "Female", profilePage.getUserGender());
        assertEquals("Toggle Notifications is correct", "false", profilePage.getUserNewsletter());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

