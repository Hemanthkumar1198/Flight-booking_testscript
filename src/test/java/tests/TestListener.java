package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utils.Log;

public class TestListener implements ITestListener {
	private static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    @Override
    public void onTestStart(ITestResult result) {
    	Log.info("Test Starter"+ result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getMethod().getMethodName());
    }

    private void takeScreenshot(String methodName) {
        if (driver == null) {
            Log.info("Driver is null, cannot take screenshot.");
            return;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //String destfile = "Screenshot/"+ methodName +"_"+timestamp+".png";
        File screenshotDir = new File("screenshots");
        
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs(); // Create folder if it doesn't exist
        }
       
        try {
            File destFile = new File(screenshotDir, methodName +"_"+timestamp+".png");
            FileUtils.copyFile(srcFile, destFile);
            Log.info("Screenshot taken: " + destFile.getAbsolutePath());
        } catch (IOException e) {
           Log.info("Error while getting screenshot");
        }
    }
	
    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("✅ Test Passed: " + result.getName());
        takeScreenshot(result.getMethod().getMethodName());
        Basetest.getextent().addScreenCaptureFromPath("screenshots/");
        Basetest.getextent().info("Browser closed");
        Basetest.getextent().log(Status.INFO, "Test execution complete");
        Basetest.getextent().pass("✅ All tests Passed");
    }
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Suite Finished: " + context.getName());
    }

}










//void onTestStart(ITestResult result);
//void onTestSuccess(ITestResult result);
//void onTestFailure(ITestResult result);
//void onTestSkipped(ITestResult result);
//void onStart(ITestContext context);
//void onFinish(ITestContext context);