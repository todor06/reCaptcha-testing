package pages;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static utils.AppConst.URL;

public class RegisterScreen {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"root\"]/section/section/div/div[1]/div[5]/button")
    WebElement registerBox;
    @FindBy(xpath = "//*[@id=\"firstName\"]")
    WebElement firstNameBox;
    @FindBy(xpath = "//*[@id=\"lastName\"]")
    WebElement lastNameBox;
    @FindBy(xpath = "//*[@id=\"email-input\"]")
    WebElement emailBox;
    @FindBy(xpath = "//*[@id=\"password1\"]")
    WebElement passwordBox;
    @FindBy(xpath = "//*[@id=\"password2\"]")
    WebElement confirmPasswordBox;
    @FindBy(xpath = "//*[@id=\"button2\"]")
    WebElement createAccButton;
    @FindBy(xpath = "/html/body")
    WebElement captcha;


    public RegisterScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    public void clickOnRegisterNow() {
        try {
            registerBox.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }


    public boolean captchaDisplayed() {
        try {
            captcha.isDisplayed();
            System.out.println("Captcha is Displayed");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void fillRegistrationFields() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(""); // here you put address of yor xmls file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 1; i < rowCount + 1; i++) {
            Row row = sheet.getRow(i);
            firstNameBox.clear();
            lastNameBox.clear();
            emailBox.clear();
            passwordBox.clear();
            confirmPasswordBox.clear();
            firstNameBox.sendKeys(row.getCell(0).getStringCellValue());
            lastNameBox.sendKeys(row.getCell(1).getStringCellValue());
            emailBox.sendKeys(row.getCell(2).getStringCellValue());
            passwordBox.sendKeys(row.getCell(3).getStringCellValue());
            confirmPasswordBox.sendKeys(row.getCell(3).getStringCellValue());
            createAccButton.click();
            Thread.sleep(1500);
            driver.get(""); // Login screen URL
            Thread.sleep(1500);
        }
    }
}

