package UserLoginTest;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProjectsPage;
import pageObjects.ViewAppliedPositionPage;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

import java.io.IOException;


public class TestRunner extends BaseClass {
    ExtentTest test;
    public static ExtentTest logInfo;
    public static Logger logger = Logger.getLogger(TestRunner.class);

    public TestRunner() {
        super();
    }

    @BeforeClass
    public void launchUrl() throws IOException {

        test = extent.createTest("launchUrl");     // Creating right side test
        logInfo = test.createNode("Launching the URL");    // Creating node which will store the screenshots
        driver = initializeDriver();
        logger.info("Driver is initialized!!");

        String urlName = properties.getProperty("url");
        driver.get(urlName);
        logger.info("Navigating to the required url!!");
        Utils.implicitWait(2);
        Utils.maximizePage();
        //Utils.extentScreenShotCapture(logInfo,"Url Launched Successfully", By.xpath("//div[@class='container top-space-50']"));
        Utils.deleteAllCookies();

    }

    @Test(priority = 1)
    public void fillDetails() throws IOException {
        logger.info("Login into the application");
        //test = extent.createTest("Log In");     // Creating right side test
        //logInfo = test.createNode("Entering user details");
        // Creating node which will store the screenshots
        LoginPage loginPage = new LoginPage();
        loginPage.fillDetails();
    }

    @Test(priority = 2)
    public void checkUrl() {
        ProjectsPage projectsPage = new ProjectsPage();
        String actual = projectsPage.checkProjectFieldBtn();
        Assert.assertEquals(actual,"https://hashedin-frontend-urtjok3rza-wl.a.run.app/employee/projects");
    }

    @Test(priority = 3)
    public void selectItems() throws InterruptedException {
        ProjectsPage projectsPage = new ProjectsPage();
        String selectTechnologyText = projectsPage.selectTechnology();
        String insideDescriptionText = projectsPage.description();
        if (insideDescriptionText.contains(selectTechnologyText))
            Assert.assertEquals(0, 0);                   // PASSING IT
        else
            Assert.fail();
    }

    @Test(priority = 4)
    public void positiveSearch() throws InterruptedException {
        ProjectsPage projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.searchForPositive());
    }

    @Test(priority = 5)
    public void negativeSearch() {
        ProjectsPage projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.searchForNegative());
    }

    @Test(priority = 6)
    public void checkApplyBtn() throws InterruptedException {
        ProjectsPage projectsPage = new ProjectsPage();
        Assert.assertEquals(projectsPage.applyForVacancy(),"Successfully Applied!");
    }

    @Test(priority = 7)
    public void validateViewAppliedUrl() {
        ViewAppliedPositionPage viewAppliedPositionPage = new ViewAppliedPositionPage();
        String actual = viewAppliedPositionPage.checkViewAppliedUrl();
        Assert.assertEquals(actual,"https://hashedin-frontend-urtjok3rza-wl.a.run.app/employee/view-applied-positions");
    }

    @Test(priority = 8)
    public void printProjectTitle() throws InterruptedException {
        ViewAppliedPositionPage viewAppliedPositionPage = new ViewAppliedPositionPage();
        System.out.println("The tittle is :" + viewAppliedPositionPage.viewProjectDetails());
        viewAppliedPositionPage.deletePosition();
    }
}

