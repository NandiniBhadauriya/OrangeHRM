package TestCases;

import TestBase.BaseClass;  // Import LoginPage class
import org.testng.Assert;
import org.testng.annotations.Test;

import PageClasses.LoginPage;
import PageClasses.SideMenuPage;

public class SideMenuTests extends BaseClass {

    @Test
    public void testSideMenu() {
        // Step 1: Login first before clicking on side menu items
        LoginPage loginPage = new LoginPage(driver);  // Create LoginPage object
        loginPage.login(p.getProperty("username"), p.getProperty("password"));  // Call the login method

        // Create an instance of the SideMenuPage
        SideMenuPage sideMenu = new SideMenuPage(driver);

        // Click on each menu and validate navigation (for now we just assert the page title or URL as an example)
        sideMenu.clickAdmin();
        Assert.assertTrue(driver.getCurrentUrl().contains("admin"), "Admin page URL is incorrect");
        
        sideMenu.clickPIM();
        Assert.assertTrue(driver.getCurrentUrl().contains("pim"), "PIM page URL is incorrect");
        
        sideMenu.clickLeave();
        Assert.assertTrue(driver.getCurrentUrl().contains("leave"), "Leave page URL is incorrect");
        
        sideMenu.clickTime();
        Assert.assertTrue(driver.getCurrentUrl().contains("time"), "Time page URL is incorrect");
        
        sideMenu.clickRecruitment();
        Assert.assertTrue(driver.getCurrentUrl().contains("recruitment"), "Recruitment page URL is incorrect");
        
        sideMenu.clickMyInfo();
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"), "My Info page URL is incorrect");
        
        sideMenu.clickPerformance();
        Assert.assertTrue(driver.getCurrentUrl().contains("performance"), "Performance page URL is incorrect");
        
        sideMenu.clickDashboard();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Dashboard page URL is incorrect");
        
        sideMenu.clickDirectory();
        Assert.assertTrue(driver.getCurrentUrl().contains("directory"), "Directory page URL is incorrect");
        
        sideMenu.clickMaintenance();
        Assert.assertTrue(driver.getCurrentUrl().contains("maintenance"), "Maintenance page URL is incorrect");
        
        sideMenu.cancel();
        
        sideMenu.clickClaim();
        Assert.assertTrue(driver.getCurrentUrl().contains("claim"), "Claim page URL is incorrect");
        
        sideMenu.clickBuzz();
        Assert.assertTrue(driver.getCurrentUrl().contains("buzz"), "Buzz page URL is incorrect");
    }
}
