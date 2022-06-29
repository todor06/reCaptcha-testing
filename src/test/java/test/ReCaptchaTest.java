package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RegisterScreen;
import pages.Setup;

import java.io.IOException;

import static utils.AppConst.URL;

public class ReCaptchaTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        driver = new Setup().startBrowser(URL);
    }

    @Test
    public void registerUserScreenFunctionality() throws IOException, InterruptedException {
        RegisterScreen regScreen = new RegisterScreen(driver);
        regScreen.clickOnRegisterNow();
        regScreen.fillRegistrationFields();
        Assert.assertTrue(regScreen.captchaDisplayed());
    }

    @AfterClass
    public void shutDown() {
        new Setup().closeBrowser(driver);
    }

}