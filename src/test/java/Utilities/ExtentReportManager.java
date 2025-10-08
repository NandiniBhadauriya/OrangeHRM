package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	// Time Stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);	//Specify location of the report
		
		sparkReporter.config().setDocumentTitle("QaFox Automation Report"); 	// Title of report
		sparkReporter.config().setReportName("QaFox Functional Testing"); 	// name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "QaFoxAutomation");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		Object testClass = result.getInstance();	// get instance create the specific class object
		WebDriver driver = ((BaseClass) testClass).driver;
		
		try {
			String imgPath = new BaseClass().captureScreen(driver, result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(IOException el) {
			el.printStackTrace();
		}
	} 
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}


//package Utilities;
//
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentReportManager implements ITestListener
//{
//	
//	public ExtentSparkReporter sparkReporter;  // UI of the report
//	public ExtentReports extent;  //populate common info on the report
//	public ExtentTest test; // creating test case entries in the report and update status of the test methods
//
//	public void onStart(ITestContext context) {
//			
//		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "\\src\\test\\java\\S9H_TestNg_Concepts.Day5\\reports\\myReport.html");//specify location of the report
//		
//		sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
//		sparkReporter.config().setReportName("Functional Testing"); // name of the report
//		sparkReporter.config().setTheme(Theme.STANDARD);
//		extent=new ExtentReports();
//		extent.attachReporter(sparkReporter);
//		extent.setSystemInfo("Computer Name","localhost");
//		extent.setSystemInfo("Environment","QA");
//		extent.setSystemInfo("Tester Name","Ruchika");
//		extent.setSystemInfo("os","Windows10");
//		extent.setSystemInfo("Browser name","Chrome");
//					
//	}
//
//
//	public void onTestSuccess(ITestResult result) {
//		
//		test = extent.createTest(result.getName()); // create a new enty in the report
//		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
//		
//	}
//
//	public void onTestFailure(ITestResult result) {
//		
//		test = extent.createTest(result.getName());
//		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
//		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
//					
//	}
//
//	public void onTestSkipped(ITestResult result) {
//
//		test = extent.createTest(result.getName());
//		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
//		
//	}
//
//	
//	public void onFinish(ITestContext context) {
//		
//		extent.flush(); // Finalizes and writes the report
//	}
//}
