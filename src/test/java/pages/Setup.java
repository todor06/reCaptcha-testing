package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Setup {
    WebDriver driver;


    public WebDriver startBrowser(String url) {

        System.out.println("Opening browser");
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }
    public void closeBrowser(WebDriver webDriver) {
        System.out.println("Closing Browser");
        webDriver.quit();
        System.out.println("Browser closed");

    }
}
