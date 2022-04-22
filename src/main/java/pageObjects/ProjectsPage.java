package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

public class ProjectsPage extends BaseClass {

    @FindBy (xpath = "//a[normalize-space()='PROJECTS']")
    WebElement projectField;

    @FindBy (xpath = "//div[@class=' css-1s2u09g-control']")
    WebElement selectTechnologyBtn;

    @FindBy (xpath = "//button[@class='button btn btn-dark btn-sm'][1]")
    WebElement project1;

    @FindBy (xpath = "//ul[@id='limheight']")
    WebElement description;

    @FindBy (xpath = "//div[@class='css-12jo7m5']")
    WebElement selectedTech;

    @FindBy(xpath = "//input[@placeholder=' Search for Project']")
    WebElement searchForBtn;

    @FindBy(xpath = "//button[normalize-space()='View Details']")
    WebElement viewDetailsBtn;

    @FindBy(xpath = "//button[@type='button'][normalize-space()='Apply']")
    WebElement applyBtn;

    @FindBy(xpath = "//div[@class='swal-title']")
    WebElement successfulMsg;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    WebElement alertOkbtn;

    @FindBy(xpath = "//div[@class='project-details-title']/child::h2/b")
    WebElement projectTitle;

    public static String projectName = null;

    public ProjectsPage() {
        PageFactory.initElements(driver,this);
    }

    public String checkProjectFieldBtn(){
        projectField.click();
        String projectsUrl = driver.getCurrentUrl();
        return projectsUrl;
    }

    public String selectTechnology() throws InterruptedException {
        Utils.wait(3000);
        //Utils.refreshPage();
        selectTechnologyBtn.click();
        Utils.wait(2000);
        Actions keyDown = new Actions(driver);
        keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
        String textSelected = selectedTech.getText();
        System.out.println("The text that is selected is " + textSelected);
        return textSelected;
    }
    public String description() throws InterruptedException {
        project1.click();
        Utils.wait(1000);
        String innerDescription = description.getText();
        return innerDescription;
    }
    public boolean searchForPositive() throws InterruptedException {
        projectField.click();
        Utils.wait(5000);
        searchForBtn.sendKeys("test");
        WebElement cardFinder = driver.findElement(By.xpath("//p[@class='ecard-text card-text']"));
        return cardFinder.isDisplayed();
    }

    public boolean searchForNegative() {
        searchForBtn.clear();
        Utils.implicitWait(5000);
        searchForBtn.sendKeys("negativeTest");
        WebElement cardFinder1 = driver.findElement(By.xpath("//b[normalize-space()='No project found with this name']"));
        return cardFinder1.isDisplayed();
    }

    public String applyForVacancy() throws InterruptedException {
        searchForBtn.clear();;
        searchForBtn.sendKeys("dont");
        viewDetailsBtn.click();
        projectName = driver.findElement(By.xpath("//div[@class='project-details-title']//h2//b")).getText();
        System.out.println("Project Name is " + projectName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", applyBtn);
        Utils.wait(5000);
        applyBtn.click();
        Utils.wait(5000);
        String actualMsg = successfulMsg.getText();
        System.out.println("actual is " + actualMsg);
        alertOkbtn.click();
        return actualMsg;
    }
}
