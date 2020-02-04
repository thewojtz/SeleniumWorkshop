package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.text.html.CSS;
import java.util.*;

public class TransferPage extends GenericPage {

    private static final String ACCOUNT_NUMBER_ID = "transfer-account";
    private static final String TRANSFER_TITLE_ID = "transfer-title";
    private static final String TRANSFER_RECIPIENT_ID = "transfer-recipent";
    private static final String TRANSFER_AMOUNT_ID = "transfer-amount";
    private static final String RADIO_BUTTON_TRANSFER_FAST_CSS = "label[for=transfer-type-fast]";
    private static final String RADIO_BUTTON_TRANSFER_STANDARD_CSS = "label[for=transfer-type-standard]";
    private static final String RADIO_BUTTON_TRANSFER_INSTANT_CSS = "label[for=transfer-type-instant]";
    private static final String CSS_SELECTOR_BUTTON = "#transfer > div > div:nth-child(9) > ul > li:nth-child(1) > input";
    private static final String PENDING_TRANSFER_TABLE_AMOUNT_VALUE_XPATH = "//*[@id=\"transfer-pending-table\"]/tbody/tr[1]/td[3]";

    int LAST_TRANSFER_AMOUNT_INDEX = 2;
    int LAST_TRANSFER_TITLE_INDEX = 1;

    public enum TRANSFER_TYPE{
        FAST, STANDARD, INSTANT
    }

    public enum PENDING_TRANSFER_HEADING{
        DATE, TITLE, AMOUNT
    }


    public TransferPage(WebDriver driver){
        super(driver);
    }

    public TransferPage fillAccountNumber(String accountNumber){
        findElementById(driver, ACCOUNT_NUMBER_ID).sendKeys(accountNumber);
        return this;
    }

    public String getFilledAccountNumber(){
        return findElementById(driver, ACCOUNT_NUMBER_ID).getAttribute("value");
    }

    public TransferPage  fillTransferTitle(String transferTitle){
        findElementById(driver, TRANSFER_TITLE_ID).sendKeys(transferTitle);
        return this;
    }

    public String getFilledTransferTitle(){
        return findElementById(driver,TRANSFER_TITLE_ID).getAttribute("value");
    }

    public TransferPage fillTransferRecipient(String transferRecipient){
        findElementById(driver, TRANSFER_RECIPIENT_ID).sendKeys(transferRecipient);
        return this;
    }

    public String getFilledTransferRecipient(){
        return findElementById(driver, TRANSFER_RECIPIENT_ID).getAttribute("value");
    }
    public TransferPage fillTransferAmount(String transferAmount){
        findElementById(driver, TRANSFER_AMOUNT_ID).sendKeys(transferAmount);
        return this;
    }

    public TransferPage selectTransferType(TRANSFER_TYPE transfer_type){
        switch (transfer_type){
            case FAST:
                findElementByCss(driver,RADIO_BUTTON_TRANSFER_FAST_CSS).click();
                return this;
            case STANDARD:
                findElementByCss(driver,RADIO_BUTTON_TRANSFER_STANDARD_CSS).click();
                return this;
            case INSTANT:
                findElementByCss(driver,RADIO_BUTTON_TRANSFER_INSTANT_CSS).click();
                return this;
        }
        return this;
    }

    public TransferPage sendTransfer(){
        waitUntilElementVisibleByCss(CSS_SELECTOR_BUTTON);
        driver.findElement(By.cssSelector(CSS_SELECTOR_BUTTON)).click();
        return this;
    }

    public String getPendingTransferAmount() {
        return findElementByXpath(driver, PENDING_TRANSFER_TABLE_AMOUNT_VALUE_XPATH).getText();
    }

    public List<String> getAllTransfers(){
        List<WebElement> transfers = new ArrayList<WebElement>();
        List<String> transferDetails = new ArrayList<String>();

        transfers.addAll(findElementById(driver, "transfer-pending-table").findElements(By.tagName("td")));

        for(WebElement element : transfers){
            transferDetails.add(element.getText());
        }
        return transferDetails;
    }

    public String getLastTransferAmount(){
        return getAllTransfers().get(LAST_TRANSFER_AMOUNT_INDEX);
    }

    public String getLastTransferTitle(){
        return getAllTransfers().get(LAST_TRANSFER_TITLE_INDEX);
    }

    public Map<PENDING_TRANSFER_HEADING, String> getLatestTransferMap() {
        List<WebElement> pendingTransferWebElementList =
                new ArrayList<WebElement>();
        Map<PENDING_TRANSFER_HEADING, String> lastPendingTransferMap =
                new HashMap<PENDING_TRANSFER_HEADING, String>();
        pendingTransferWebElementList.
                addAll(findElementById(driver, "transfer-pending-table")
                        .findElements(By.tagName("td")));
        if(pendingTransferWebElementList.size() > 0) {
            lastPendingTransferMap.
                    put(PENDING_TRANSFER_HEADING.DATE,
                            pendingTransferWebElementList.
                                    get(0).getText());
            lastPendingTransferMap.
                    put(PENDING_TRANSFER_HEADING.TITLE,
                            pendingTransferWebElementList.
                                    get(1).getText());
            lastPendingTransferMap.
                    put(PENDING_TRANSFER_HEADING.AMOUNT,
                            pendingTransferWebElementList.
                                    get(2).getText());
        }
        return lastPendingTransferMap;
    }

    public Map<Integer, Map<PENDING_TRANSFER_HEADING, String>> getAllPendingTransfersMap() {
        List<WebElement> pendingTransferWebElementList =
                new ArrayList<WebElement>();
        Map<Integer, Map<PENDING_TRANSFER_HEADING, String>> allPendingTransferMap =
                new HashMap<Integer, Map<PENDING_TRANSFER_HEADING, String>>();
        pendingTransferWebElementList.
                addAll(findElementById(driver, "transfer-pending-table")
                        .findElements(By.tagName("td")));
        if(pendingTransferWebElementList.size() > 0) {
            for(int i=0; i < pendingTransferWebElementList.size(); i+=3) {
                Map<PENDING_TRANSFER_HEADING, String> rowTransferMap =
                        new HashMap<PENDING_TRANSFER_HEADING, String>();
                rowTransferMap.
                        put(PENDING_TRANSFER_HEADING.DATE,
                                pendingTransferWebElementList.
                                        get(i).getText());
                rowTransferMap.
                        put(PENDING_TRANSFER_HEADING.TITLE,
                                pendingTransferWebElementList.
                                        get(i+1).getText());
                rowTransferMap.
                        put(PENDING_TRANSFER_HEADING.AMOUNT,
                                pendingTransferWebElementList.
                                        get(i+2).getText());
                allPendingTransferMap.put(i/3, rowTransferMap);
            }
        }
        return allPendingTransferMap;
    }
}
