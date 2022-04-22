package resources.baseClass;


import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;
import resources.utils.Utils;
import testAutomation.ExtentReportListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass extends ExtentReportListener {
    public static WebDriver driver;
    public static Properties properties;

    @BeforeTest
    public WebDriver initializeDriver() {
        try {
            String propertyFile = "\\src\\main\\java\\resources\\config.properties";
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + propertyFile);
            properties = new Properties();
            properties.load(fileInputStream);
            String log4jPath=properties.getProperty("log4jPropertiesFile");
            PropertyConfigurator.configure(log4jPath);
        } catch (FileNotFoundException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
        String browserName = properties.getProperty("browser");

        /*Checking for specified browser name*/
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromePath"));
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("firefoxPath"));
            driver = new FirefoxDriver();
        } else if (browserName.equals("IE")) {
            System.setProperty("webdriver.ie.driver", properties.getProperty("iePath"));
            driver = new InternetExplorerDriver();
        }
        Utils.implicitWait(3);
        return driver;
    }
}

