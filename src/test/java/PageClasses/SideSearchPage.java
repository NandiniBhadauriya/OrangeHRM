package PageClasses;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SideSearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SideSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(css = "input.oxd-input.oxd-input--active[placeholder='Search']")
    WebElement sideSearchInput;

    public void enterSearchText(String text) {
        wait.until(ExpectedConditions.visibilityOf(sideSearchInput));
        wait.until(ExpectedConditions.elementToBeClickable(sideSearchInput));
        sideSearchInput.click();
        sideSearchInput.sendKeys(Keys.chord(Keys.CONTROL, "a")); // select all
        sideSearchInput.sendKeys(Keys.BACK_SPACE); // clear text
        sideSearchInput.sendKeys(text);
    }

    public boolean isMenuVisible(String menuName) {
        try {
            // Keep XPath for text match as CSS cannot match exact text
            By menuLocator = By.xpath("//span[text()='" + menuName + "']");
            WebElement menuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));
            return menuItem.isDisplayed();
        } catch (Exception e) {
            System.out.println("Menu not visible: " + menuName + " - " + e.getMessage());
            return false;
        }
    }

    public void clearSearch() {
        sideSearchInput.clear();
    }
}
