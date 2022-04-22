package resources.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import resources.baseClass.BaseClass;

import java.io.IOException;
import java.time.Duration;

public class Utils extends BaseClass
{

    public static void implicitWait(int seconds)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }


    public static void highlightElement(WebElement guide)
    {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].style.border='2px solid red'", guide);

    }

    /*To refresh page*/
    public static void refreshPage()
    {
        driver.navigate().refresh();
    }


    /*To maximize page*/
    public static void maximizePage()
    {
        driver.manage().window().maximize();
    }

    /*To delete all cookies*/
    public static void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    /*To navigate back to previous page*/
    public static void navigateBack()
    {
        driver.navigate().back();
    }


    /*To wait for certain seconds*/
    public static void wait(int timeInMilliSeconds) throws InterruptedException {
        Thread.sleep(timeInMilliSeconds);
    }


    public static void extentScreenShotCapture(ExtentTest logInfo,String logInfoMsg,By guide) throws IOException {

        WebElement element_node = driver.findElement(guide);
        Utils.highlightElement(element_node);
        logInfo.pass(logInfoMsg);
        logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

    }
 }

