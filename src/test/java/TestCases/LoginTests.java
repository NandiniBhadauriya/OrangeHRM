package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import PageClasses.LoginPage;
import TestBase.BaseClass;

public class LoginTests extends BaseClass {

    @Test
    public void verifyLoginFunctionality() {
    	logger.info("---Login Test Started----");
    	logger.info("this is a debug log messagae");
    	
        // Step 1: Check Page Title
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, "OrangeHRM", "Title does not match");

        // Step 2: Login using Page Object
        LoginPage lp = new LoginPage(driver);
        lp.login(p.getProperty("username"), p.getProperty("password"));

        // Step 3: Wait for Dashboard element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        } catch (Exception e) {
            System.out.println("Dashboard element not found. Login may have failed.");
        }

        // Step 4: Assert that we reached the dashboard
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentURL);

        Assert.assertTrue(currentURL.contains("dashboard"), "Login might have failed!");
    }
}

//package TestCases;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import PageClasses.LoginPage;
//import TestBase.BaseClass;
//
//public class LoginTests extends BaseClass {
//
//    @BeforeClass
//    void setup() {
//        initialization();  // From BaseClass
//    }
//
//    @Test(priority = 1)
//    void testTitle() {
//        String title = driver.getTitle();
//        System.out.println("Page Title: " + title);
//        Assert.assertEquals(title, "OrangeHRM");
//    }
//
//    @Test(priority = 2)
//    void testLogin() {
//        LoginPage lp = new LoginPage(driver);
//        lp.login(prop.getProperty("username"), prop.getProperty("password"));
//        
//        // Wait thoda do page load hone ka
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        String currentUrl = driver.getCurrentUrl();
//        System.out.println("Current URL after login: " + currentUrl);
//
//        Assert.assertTrue(currentUrl.contains("dashboard"), "Login might have failed!");
//    }
//
//    @AfterClass
//    void tearDown() {
//            driver.quit();
//        }
//    }
//
