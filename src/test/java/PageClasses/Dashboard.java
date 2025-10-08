package PageClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
    
    WebDriver driver;

    // Constructor initializes PageFactory elements
    public Dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Example: Identify an element unique to Home Page to confirm login success
    // For OrangeHRM, maybe the dashboard header or welcome message, e.g.:
    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboardHeader;

    // You can add a method to check if the element is displayed
    public boolean isDashboardDisplayed() {
        return dashboardHeader.isDisplayed();
    }

    // Optionally, get the page title
    public String getHomePageTitle() {
        return driver.getTitle();
    }
    // Method to get current URL
    public String getDashboardUrl() {
        return driver.getCurrentUrl();
}
}
