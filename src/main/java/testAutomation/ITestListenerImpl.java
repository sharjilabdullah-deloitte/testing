package testAutomation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.baseClass.BaseClass;

import java.io.IOException;

public class ITestListenerImpl extends BaseClass implements ITestListener
{
	public static ExtentReports extent;
	ExtentTest testcase;

	public void onTestStart(ITestResult result) {
		testcase=extent.createTest(result.getMethod().getMethodName()); 		//remv if fail
		//extent.flush();
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("PASS");
	}

	public void onTestFailure(ITestResult result) {
		testcase.log(Status.FAIL, "Test is failed");
		extent.flush();
		ExtentReportListener extentReportListener=new ExtentReportListener();
		try {
			extentReportListener.captureScreenShot(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		testcase.log(Status.SKIP, "Test is skipped");
		System.out.println("SKIP");

	}


	public void onStart(ITestContext context) {
		System.out.println("Execution started....");
		extent= setUp();
	}

	public void onFinish(ITestContext context) {
		System.out.println("Execution completed...");
		extent.flush();		
		System.out.println("Generated Report. . .");	
		
	}
}
