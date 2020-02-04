package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MenuPage extends GenericPage{



    public MenuPage(WebDriver driver){
        super(driver);
    }

    public TransferPage selectTransfersPage() {
        findElementByLinkText(driver, "TRANSFER").click();
        return new TransferPage(driver);
    }

    public ProfilePage selectProfilePage() {
        findElementByLinkText(driver, "PROFILE").click();
        return new ProfilePage(driver);
    }

}
