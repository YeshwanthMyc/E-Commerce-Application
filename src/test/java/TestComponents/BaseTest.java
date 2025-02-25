package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectModel.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage loginPage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis =
				new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GlobalProperties\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			this.driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			this.driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		initializeDriver();
		this.loginPage = new LandingPage(driver);
		loginPage.landingPage();
		return loginPage;
	}
	
	@AfterMethod
	public void closeBrowser() throws IOException {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {
		File file = new File(filePath);
		String jsonData = FileUtils.readFileToString(file, "UTF-8");
		
		//convert string to hashmap 
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data  =mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\TestData\\Data.json");
		return new Object[][] {{data.get(0)}};
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();
	}
	
}
