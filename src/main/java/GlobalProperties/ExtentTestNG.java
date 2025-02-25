package GlobalProperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestNG {
	
	public ExtentReports getReportObject() {
		String file = System.getProperty("user.dir")+"\\Reports\\index2.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		reporter.config().setDocumentTitle("E-Commerce Application Test Results");
		reporter.config().setReportName("Purchase Order Test Results");
		report.setSystemInfo("Tester", "Yeshwanth");
		return report;
	}

}
