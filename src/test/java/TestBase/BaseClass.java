package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Logger logger;
	public WebDriver driver;
	public Properties p;
	
	@BeforeClass
//	@Parameters({"browser"})
//	public void setup(String br) throws IOException {
	@Parameters({"browser"})
	public void setup(@Optional("chrome") String br) throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		
//		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		logger =LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) {
		case "chrome":
			ChromeOptions coptions = new ChromeOptions();
			coptions.addArguments("--headless=false");
			coptions.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(coptions);
			break;
		
		case "firefox":
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.addArguments("--headless=true");
			foptions.addArguments("--window-size=1920,1080");
			driver = new FirefoxDriver(foptions);
			break;
			
		default: System.out.println("No matching browser..."); 
			return;
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
	}
	
	public String captureScreen(WebDriver driver, String tname) throws IOException {
		System.out.println("Capturing screnshot....");
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String targetDir = System.getProperty("user.dir") + "/screenshots/";
		File dir = new File(targetDir);
			if(!dir.exists()) {
				dir.mkdir();
			}
			
			String targetFilePath = targetDir + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
			
			FileUtils.copyFile(sourceFile, targetFile);
			System.out.println("Screenshot saved at: " + targetFilePath);
			return targetFilePath;
		}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

//package TestBase;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class BaseClass {
//    public static WebDriver driver;
//    public static Properties prop = new Properties();
//
//    // Static block to load config.properties once
//    static {
//        try {
//            // Load from classpath (works across environments)
//            InputStream input = BaseClass.class.getClassLoader().getResourceAsStream("config.properties");
//
//            if (input == null) {
//                throw new RuntimeException("config.properties file not found in classpath!");
//            }
//
//            prop.load(input);
//            
//            System.out.println(" config.properties loaded successfully.");
//            System.out.println("URL from config: " + prop.getProperty("url"));
//        } catch (IOException e) {
//            System.out.println(" Failed to load config.properties");
//            e.printStackTrace();
//        }
//    }
//
//    // Initializes the WebDriver and opens the URL
//    public void initialization() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//
//        String url = prop.getProperty("url");
//        if (url == null || url.trim().isEmpty()) {
//            throw new RuntimeException(" URL is not specified in config.properties");
//        }
//
//        driver.get(url);
//        System.out.println(" Navigated to: " + url);
//    }
//}
