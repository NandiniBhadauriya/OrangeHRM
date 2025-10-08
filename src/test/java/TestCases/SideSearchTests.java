package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClasses.LoginPage;
import PageClasses.SideSearchPage;
import TestBase.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SideSearchTests extends BaseClass {

    LoginPage loginPage;
    SideSearchPage sideSearchPage;
    WebDriverWait wait;

    @BeforeClass
    public void setupLogin() {
        try {
            loginPage = new LoginPage(driver);
            sideSearchPage = new SideSearchPage(driver);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Login steps
            loginPage.setUserName(p.getProperty("username"));
            loginPage.setPassword(p.getProperty("password"));
            loginPage.clickLogin();

            // Wait for Dashboard header to confirm login success
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

            // Small pause to ensure page fully loads
            Thread.sleep(2000);

            // If search input is inside an iframe, uncomment and update this line:
            // driver.switchTo().frame("iframe_id_or_name");

            // Try waiting for search input by CSS selector first
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.oxd-input.oxd-input--active[placeholder='Search']")));
                System.out.println("Search input found by CSS selector");
            } catch (Exception e) {
                System.out.println("CSS selector failed, trying XPath locator");
                // Fallback XPath locator
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
                System.out.println("Search input found by XPath locator");
            }

            // If switched to iframe, switch back to default content
            // driver.switchTo().defaultContent();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setup failed: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void verifyMenusVisibleInSearch() {
        sideSearchPage.enterSearchText("Admin");
        Assert.assertTrue(sideSearchPage.isMenuVisible("Admin"), "'Admin' menu not visible");
        sideSearchPage.clearSearch();

        sideSearchPage.enterSearchText("PIM");
        Assert.assertTrue(sideSearchPage.isMenuVisible("PIM"), "'PIM' menu not visible");
        sideSearchPage.clearSearch();

        sideSearchPage.enterSearchText("Leave");
        Assert.assertTrue(sideSearchPage.isMenuVisible("Leave"), "'Leave' menu not visible");
        sideSearchPage.clearSearch();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
