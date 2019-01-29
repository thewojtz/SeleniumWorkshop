package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static helpers.Configuration.getConfiguration;
import static helpers.Driver.initializeWebDriver;

public class ParametrizedTransferTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = initializeWebDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS) ;
        driver.get(getConfiguration().getSiteURL());
    }

    //@ParameterizedTest
    //@ValueSource(strings = "name", "torororororo", "341414434%#@%#@")
}
