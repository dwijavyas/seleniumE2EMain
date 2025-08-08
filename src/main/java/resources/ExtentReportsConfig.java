package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {

	
	public static ExtentReports getReportObject() {
		
		String erPath = "Ereports//index.html"; 
		ExtentSparkReporter reporter = new ExtentSparkReporter(erPath);
		reporter.config().setDocumentTitle("Automation Results");
		reporter.config().setReportName("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "DV");
		return extent;
	}
	
	
	
}
