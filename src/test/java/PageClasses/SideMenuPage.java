package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideMenuPage {

    WebDriver driver;

    public SideMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminMenu;

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimMenu;

    @FindBy(xpath = "//span[text()='Leave']")
    WebElement leaveMenu;

    @FindBy(xpath = "//span[text()='Time']")
    WebElement timeMenu;

    @FindBy(xpath = "//span[text()='Recruitment']")
    WebElement recruitmentMenu;

    @FindBy(xpath = "//span[text()='My Info']")
    WebElement myInfoMenu;

    @FindBy(xpath = "//span[text()='Performance']")
    WebElement performanceMenu;

    @FindBy(xpath = "//span[text()='Dashboard']")
    WebElement dashboardMenu;

    @FindBy(xpath = "//span[text()='Directory']")
    WebElement directoryMenu;

    @FindBy(xpath = "//span[text()='Maintenance']")
    WebElement maintenanceMenu;

    @FindBy(xpath = "//span[text()='Claim']")
    WebElement claimMenu;

    @FindBy(xpath = "//span[text()='Buzz']")
    WebElement buzzMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/form/div[4]/button[1]")
    WebElement ClickCancel;

    // Click methods for each menu item

    public void clickAdmin() {
        adminMenu.click();
    }

    public void clickPIM() {
        pimMenu.click();
    }

    public void clickLeave() {
        leaveMenu.click();
    }

    public void clickTime() {
        timeMenu.click();
    }

    public void clickRecruitment() {
        recruitmentMenu.click();
    }

    public void clickMyInfo() {
        myInfoMenu.click();
    }

    public void clickPerformance() {
        performanceMenu.click();
    }

    public void clickDashboard() {
        dashboardMenu.click();
    }

    public void clickDirectory() {
        directoryMenu.click();
    }

    public void clickMaintenance() {
        maintenanceMenu.click();  
    }
    
    public void cancel() {
       ClickCancel .click(); 
    }

    public void clickClaim() {
        claimMenu.click();
    }

    public void clickBuzz() {
        buzzMenu.click();
        
        
    }
}