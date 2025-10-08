package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    WebElement txt_username;

    @FindBy(name = "password")
    WebElement txt_password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_login;

    public void setUserName(String username) {
        txt_username.clear();
        txt_username.sendKeys(username);
    }

    public void setPassword(String password) {
        txt_password.clear();
        txt_password.sendKeys(password);
    }

    public void clickLogin() {
        btn_login.click();
    }

    public void login(String username, String password) {
        setUserName(username);
        setPassword(password);
        clickLogin();
    }
}
//package PageClasses;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class LoginPage {
//    WebDriver driver;
//
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    @FindBy(name = "username")
//    WebElement txt_username;
//
//    @FindBy(name = "password")
//    WebElement txt_password;
//
//    @FindBy(xpath = "//button[@type='submit']")
//    WebElement btn_login;
//
//    public void setUserName(String username) {
//        txt_username.sendKeys(username);
//    }
//
//    public void setPassword(String password) {
//        txt_password.sendKeys(password);
//    }
//
//    public void clickLogin() {
//        btn_login.click();
//    }
//
//    public void login(String username, String password) {
//        setUserName(username);
//        setPassword(password);
//        clickLogin();
//    }
//}