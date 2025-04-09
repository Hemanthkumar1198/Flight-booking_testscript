package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
	
private static WebDriverManager instance;
	private WebDriver driver;
	
	private WebDriverManager() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static WebDriverManager getdriverInstance() {
		if(instance==null) {
			instance = new WebDriverManager();
		}
		return instance;
	}
	
	public WebDriver getdriver() {
		return driver;
	}
}
