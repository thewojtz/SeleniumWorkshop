package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MenuPage;
import pages.TransferPage;

import java.util.concurrent.TimeUnit;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;
import static org.junit.Assert.assertEquals;

public class TransferTest implements  TransferTestData{

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = initializeWebDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS) ;
        driver.get(getConfiguration().getSiteURL());
    }

    @Test
    public void sendTransferAndCheckIsCorrect() {

        MenuPage menuPage = new MenuPage(driver);
        TransferPage transferPage = menuPage.selectTransfersPage();
        transferPage.fillAccountNumber(ACCOUNT_NUMBER)
            .fillTransferTitle(TRANSFER_TITLE)
            .fillTransferRecipient(TRANSFER_RECIPIENT)
            .fillTransferAmount(TRANSFER_AMOUNT)
            .selectTransferType(TransferPage.TRANSFER_TYPE.FAST)
            .sendTransfer();
        /*
        assertEquals(TRANSFER_AMOUNT_IS_CORRECT,
                TRANSFER_AMOUNT,
                transferPage.getLastTransferAmount());
        assertEquals(TRANSFER_TITLE_IS_CORRECT,
                TRANSFER_TITLE,
                transferPage.getLastTransferTitle());

        assertEquals("Transfer amount is not correct",
                TRANSFER_AMOUNT,
                transferPage.getLatestTransferMap()
                        .get(TransferPage.PENDING_TRANSFER_HEADING.AMOUNT));
        assertEquals("Transfer title is not correct",
                TRANSFER_TITLE,
                transferPage.getLatestTransferMap()
                        .get(TransferPage.PENDING_TRANSFER_HEADING.TITLE));
                        */

        assertEquals("Transfer amount is not correct",
                TRANSFER_AMOUNT,
                transferPage.getAllPendingTransfersMap()
                        .get(0).get(TransferPage.PENDING_TRANSFER_HEADING.AMOUNT));
        assertEquals("Transfer title is not correct",
                TRANSFER_TITLE,
                transferPage.getAllPendingTransfersMap()
                        .get(0).get(TransferPage.PENDING_TRANSFER_HEADING.TITLE));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
