package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pages.LoginPage;


public class BaseTest {

	public WebDriver driver;
	public LoginPage lp;
	
	
	public WebDriver setUp() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\GlobalProp.properties");
		prop.load(fis);
		
		//java ternary operator
		String browserName = System.getProperty("browser") != null ? 
				System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.startsWith("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) 
			{
			options.addArguments("--headless");
			}
		driver = new ChromeDriver(options);
		
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
		driver = new FirefoxDriver();
			
		}
		else if (browserName.equalsIgnoreCase("edge")) {
		
		driver = new EdgeDriver();
			
		}
				
		driver.manage().window().setSize(new Dimension(1440, 900));//full screen headless
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;	
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"/Screenshots/" +testcasename+ ".png";
		File dest = new File(destPath);
		FileUtils.copyFile(src, dest);
		return destPath;
		
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		
		//convert json datat to string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		//string to hashmap - jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> hmdata = 
				mapper.readValue(jsonContent, new 
						TypeReference<List<HashMap<String, String>>>(){
					
				});
		return hmdata;
		
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage loginApplication() throws IOException {
		
		driver = setUp();
		lp = new LoginPage(driver);
		lp.goTo();
		return lp;
	}
	
	
	  @AfterMethod(alwaysRun=true) public void tearDown() {
	  
	  driver.quit();
	  
	  }
	 
	
	
	
	
	
}
