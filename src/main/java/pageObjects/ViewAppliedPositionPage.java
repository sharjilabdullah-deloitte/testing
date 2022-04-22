package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.baseClass.BaseClass;
import resources.utils.Utils;

public class ViewAppliedPositionPage extends BaseClass {

    @FindBy(xpath = "//a[normalize-space()='VIEW APPLIED POSITION']")
    WebElement viewAppliedBtn;

    @FindBy(xpath = "//div[@class='project-detailstitle']//h2//b")
    WebElement projectName;

    @FindBy(xpath = "(//button[text()='View Project Details'])[1]")
    WebElement viewProjectBtn;

    @FindBy(xpath = "(//button[normalize-space()='Delete'])[1]")
    WebElement deleteBtn;

    @FindBy(xpath = "//button[@class='swal-button swal-button--cancel']")
    WebElement cancelBtn;

    @FindBy(xpath = "//div[@class='swal-button-container']")
    WebElement cancelOkBtn;

    @FindBy(xpath = "//button[@class='swal-button swal-button--confirm swal-button--danger']")
    WebElement confirmOkBtn;

    public ViewAppliedPositionPage(){
        PageFactory.initElements(driver,this);
    }

    public String checkViewAppliedUrl(){
        viewAppliedBtn.click();
        String url = driver.getCurrentUrl();
        return url;
    }

    public String viewProjectDetails() {
        viewProjectBtn.click();
        Utils.implicitWait(5);
        String actual = projectName.getText();
        return actual;
    }
    public void deletePosition() throws InterruptedException {
        deleteBtn.click();
        Utils.wait(5000);
        cancelBtn.click();
        cancelOkBtn.click();
        Utils.wait(5000);
        deleteBtn.click();
        confirmOkBtn.click();
    }
}
