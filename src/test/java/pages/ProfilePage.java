package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;



public class ProfilePage extends GenericPage {

    int NAME_INDEX = 0;
    int STREET_INDEX = 1;
    int CITY_INDEX = 2;
    int POSTAL_CODE_INDEX = 3;
    int EMAIL_INDEX = 4;
    int COUNTRY_INDEX = 5;
    int GENDER_INDEX = 6;
    int NEWSLETTER_INDEX = 7;
    int NOTE_INDEX = 8;

    public enum GENDER_CHOICE{
        MALE, FEMALE, DONT_ASK
    }
    @FindBy(id="update-name")
    WebElement profileName;

    @FindBy(id="update-country")
    WebElement updateCountry;

    @FindBy(id="update-street")
    WebElement updateStreet;

    @FindBy(id="update-city")
    WebElement updateCity;

    @FindBy(id="update-postalcode")
    WebElement updatePostalCode;

    @FindBy(id="update-additional")
    WebElement updateAdditional;

    @FindBy(id="update-email")
    WebElement updateEmail;

    @FindBy(xpath = "//*[@id=\"main\"]/div/section/div/div[1]/form/div/div[12]/ul/li[1]/input")
    WebElement saveButton;

    @FindBy(css="label[for=update-gender-male]")
    WebElement maleGender;

    @FindBy(css="label[for=update-gender-female]")
    WebElement femaleGender;

    @FindBy(css="label[for=update-gender-dont]")
    WebElement dontAskGender;

    @FindBy(css="label[for=update-newsletter]")
    WebElement toggleNotifications;

    public ProfilePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    };
    public ProfilePage updateName(String name){
        profileName.sendKeys(name);
        return this;
    }
    public ProfilePage updateCountry(String selectedCountry){
        Select countryDropdown = new Select(updateCountry);
        countryDropdown.selectByVisibleText(selectedCountry);
        return this;
    }
    public ProfilePage updateStreet(String streetName){
        updateStreet.sendKeys(streetName);
        return this;
    }
    public ProfilePage updateCity(String cityName){
        updateCity.sendKeys(cityName);
        return this;
    }
    public ProfilePage updatePostalCode(String postalCode){
        updatePostalCode.sendKeys(postalCode);
        return this;
    }
    public ProfilePage updateEmail(String email){
        updateEmail.sendKeys(email);
        return this;
    }
    public ProfilePage updateAdditional(String additional){
        updateAdditional.sendKeys(additional);
        return this;
    }
    public ProfilePage saveUser(){
        saveButton.click();
        return this;
    }
    public ProfilePage selectGender(GENDER_CHOICE gender_choice) {
        switch (gender_choice){
            case MALE:
                maleGender.click();
                return this;
            case FEMALE:
                femaleGender.click();
                return this;
            case DONT_ASK:
                dontAskGender.click();
                return this;
        }
        return this;
    }
    public ProfilePage toggleNotifications(boolean b) {
        if(b==false){
            toggleNotifications.click();
        }
        return this;
    }

    public List<String> getUserInfo(){
        List<WebElement> userInfo = new ArrayList<WebElement>();
        List<String> userInfoDetails = new ArrayList<String>();

        userInfo.addAll(findElementById(driver, "personal-data-table").findElements(By.tagName("td")));

        for(WebElement element : userInfo){
            userInfoDetails.add(element.getText());
        }
        return userInfoDetails;
    }

    public String getUserName(){
        return getUserInfo().get(NAME_INDEX);
    }
    public String getUserStreet(){
        return getUserInfo().get(STREET_INDEX);
    }
    public String getUserCity(){
        return getUserInfo().get(CITY_INDEX);
    }
    public String getUserPostalCode(){
        return getUserInfo().get(POSTAL_CODE_INDEX);
    }
    public String getUserEmail(){
        return getUserInfo().get(EMAIL_INDEX);
    }
    public String getUserCountry(){
        return getUserInfo().get(COUNTRY_INDEX);
    }
    public String getUserGender(){
        return getUserInfo().get(GENDER_INDEX);
    }
    public String getUserNewsletter(){
        return getUserInfo().get(NEWSLETTER_INDEX);
    }
    public String getUserNote(){
        return getUserInfo().get(NOTE_INDEX);
    }
}
