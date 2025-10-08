package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClasses.Dashboard;
import PageClasses.LoginPage;
import TestBase.BaseClass;

public class DashboardTests extends BaseClass {

    LoginPage loginPage;
    Dashboard dashboard;

    @BeforeClass
    public void setUp() throws Exception {
        System.out.println(" == setUp started in DashboardTests == ");
        // Call BaseClass setup method directly
        setup("chrome");
        System.out.println(" setup(\"chrome\") done ");

        loginPage = new LoginPage(driver);
        System.out.println(" loginPage initialized: " + loginPage);

        dashboard = new Dashboard(driver);
        System.out.println(" dashboard initialized: " + dashboard);

        // Try login
        String username = p.getProperty("username");
        String password = p.getProperty("password");
        System.out.println(" Using credentials: " + username + " / " + password);
        loginPage.login(username, password);
        System.out.println(" loginPage.login called ");
    }

    @Test
    public void verifyDashboardPageAfterLogin() {
        System.out.println(" Starting verifyDashboardPageAfterLogin ");
        // Dashboard visible?
        boolean disp = dashboard.isDashboardDisplayed();
        System.out.println(" dashboard.isDashboardDisplayed() = " + disp);
        Assert.assertTrue(disp, "Dashboard header is NOT visible!");

        // Title
        String actualTitle = dashboard.getHomePageTitle();
        System.out.println(" Title: " + actualTitle);
        Assert.assertTrue(actualTitle.contains("OrangeHRM"), "Page title mismatch!");

        // URL
        String actualUrl = dashboard.getDashboardUrl();
        System.out.println(" URL: " + actualUrl);
        Assert.assertTrue(actualUrl.contains("/dashboard"), "URL does not contain '/dashboard'!");
    }
     
    	}
    


//package TestCases;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import PageClasses.Dashboard;
//import PageClasses.LoginPage;
//import TestBase.BaseClass;
//
//public class DashboardTests extends BaseClass {
//
//	LoginPage loginPage;
//	Dashboard dashboard;
//
//	public DashboardTests() {
//		super(); // Load config properties
//	}
//
//	@BeforeClass
//	public void setUp() {
//		initialization();
//		loginPage = new LoginPage(driver);
//		dashboard = new Dashboard(driver);
//
//		// Perform login before dashboard validation
//		loginPage.setUserName(prop.getProperty("username"));
//		loginPage.setPassword(prop.getProperty("password"));
//		loginPage.clickLogin();
//	}
//
//	@Test(priority = 1)
//	public void verifyDashboardIsDisplayed() {
//		boolean displayed = dashboard.isDashboardDisplayed();
//		System.out.println("Dashboard displayed: " + displayed);
//		Assert.assertTrue(displayed, "Dashboard header is NOT displayed!");
//	}
//
//	@Test(priority = 2)
//	public void verifyDashboardTitle() {
//		String title = dashboard.getHomePageTitle();
//		System.out.println("Current Page Title: " + title);
//		Assert.assertTrue(title.contains("OrangeHRM"), "Title mismatch!");
//	}
//
//	@Test(priority = 3)
//	public void verifyDashboardUrl() {
//		String url = dashboard.getDashboardUrl();
//		System.out.println("Current URL: " + url);
//		Assert.assertTrue(url.contains("/dashboard"), "URL does not contain '/dashboard'!");
//	}
//
//	@AfterClass
//	public void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
//}
//
