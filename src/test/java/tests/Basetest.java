package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pages.Confirmationpage;
import pages.Flightbookingpage;
import pages.Homepage;
import pages.Purchasepage;
import utils.ExtentManager;
import utils.WebDriverManager;

public class Basetest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	protected Homepage homepage;
	protected Flightbookingpage flightBookingPage;
	protected Purchasepage purchasePage;
	protected Confirmationpage confirmationPage;
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    @BeforeMethod
    public void setup(ITestContext context) {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = WebDriverManager.getdriverInstance().getdriver();
        TestListener.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        homepage = new Homepage(driver);
        flightBookingPage = new Flightbookingpage(driver);
        purchasePage = new Purchasepage(driver);
        confirmationPage = new Confirmationpage(driver);
        
        
        extent = ExtentManager.getExtent();
        test = extent.createTest(context.getName());
        extentTest.set(test);
        
        driver.get("https://blazedemo.com/");
        
    }

	@AfterMethod
	public void teardown() {
		driver.quit();
		extent.flush();
	}
	
	public static ExtentTest getextent() {
		return extentTest.get();
		
	}
}