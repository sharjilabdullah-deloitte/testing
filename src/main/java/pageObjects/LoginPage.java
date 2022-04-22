package pageObjects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginPage extends BaseClass {

    @FindBy(xpath = "//input[@id='username']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void fillDetails() throws IOException {
        String excelFilePath = properties.getProperty("userExcelPath"); //Locating Excel File path
        FileInputStream fis = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        String emailValue = null;
        int passwordValue = 0;

        int rowCount = sheet.getPhysicalNumberOfRows();       //Using Apache POI to fetch the data
        int columnCount = sheet.getRow(0).getLastCellNum();
        for (int row = 1; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (column == 0)
                    emailValue = sheet.getRow(row).getCell(column).getStringCellValue();
                if (column == 1)
                    passwordValue = (int) sheet.getRow(row).getCell(column).getNumericCellValue();
            }
        }
        usernameField.sendKeys(emailValue);
        Utils.implicitWait(3);
        passwordField.sendKeys(String.valueOf(passwordValue));
        loginBtn.click();
    }
}
