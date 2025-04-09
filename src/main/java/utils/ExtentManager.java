package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	private ExtentManager() {

	}

	public static ExtentReports getExtent() {
		if (extent == null) {
			String reportpath = System.getProperty("user.dir") + "/target/ExtentReport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportpath);
			spark.config().setReportName("Flight_Booking_Project");
			spark.config().setDocumentTitle("Test_automation_results");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "HK");
			extent.setSystemInfo("Environment", "QA_FT");
		}
		return extent;
	}

}
