package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReportsConfig;

public class Listeners extends BaseTest implements ITestListener {

	//creates an entry for tests as first step
	ExtentTest test;
	//calling er method directly as its static
	ExtentReports extent = ExtentReportsConfig.getReportObject();
	//wheneverr the tests run in parallel, listenears can override and ss may be placed in another test instead of actual failed test
	//thus threadlocal class by making object thread safe
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	
	@Override
	public void onTestStart(ITestResult result) {
	
		test = extent.createTest(result.getMethod().getMethodName());;
		extentTest.set(test);//tl will assign unique threadid to test object 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		//collect the test method name into test object and log results
		test.log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		//print error msg if failed
		extentTest.get().fail(result.getThrowable());
		
		//get ss driver life - from the testmethod thats running/failing
		//fields are associated with classes only 
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String ssPath = null;
		try {
			ssPath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
		//screenshot
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

	
	
	
}
