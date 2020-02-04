package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericPage {

    protected WebDriver driver;
    public static final Integer EXPLICIT_WAIT_TIMEOUT = 30;
    //public GenericPage() {}

    public GenericPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElementByLinkText(WebDriver driver, String linkText){
        return driver.findElement(By.linkText(linkText));
    }

    public  WebElement findElementById(WebDriver driver, String id){
        return driver.findElement(By.id(id));
    }

    public  WebElement findElementByXpath(WebDriver driver, String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public  WebElement findElementByCss(WebDriver driver, String css){
        return driver.findElement(By.cssSelector(css));
    }

    public void waitUntilElementVisibleById(String idOfElement){
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idOfElement)));
    }

    public void waitUntilElementVisibleByCss(String cssOfElement){
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssOfElement)));
    }
    //public WebElement findElementByInputValue(WebDriver driver, String inputValue){
    //    return driver.findElement(By.cssSelector("#transfer > div > div:nth-child(9) > ul > li:nth-child(1) > input"));
    //}
}
