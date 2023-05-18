package driverFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.LoggerLoad;

public class DriverSetup {
	public static WebDriver driver;

	public WebDriver getdesireDriver(String browser) throws InterruptedException {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.out.println("chromebrowser");
			LoggerLoad.info("enter getchromedriver");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		
		}
		/*else if(browsertype.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//tlDriver.set(new FirefoxDriver());
			driver=new FirefoxDriver();*/
		else if (browser.equalsIgnoreCase("edge")) {
				//Edge
			//System.setProperty("webdriver.msedge.driver", "C:/Users/sange/OneDrive/Desktop/Drivers/msedgedriver.exe");
			System.out.println("edgebrowser");
			WebDriverManager.edgedriver().setup();	
			driver=new EdgeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;	
		
	}	
		
	public  static void openPage(String url) {
		LoggerLoad.info("current page :"+url);
		getDriver().get(url);
	}
	public  static String getdriverTitle() {
		return getDriver().getTitle();
	}
	public  static void NavBack() {
		getDriver().navigate().back();
	}
	public static WebDriver getDriver() {
		return driver;
		//LoggerLoad.info("enter getdriver");
		//return tlDriver.get();
	}
	
	public void setUpDriver(String browser) {
		//if (getDriver()==null) {
		System.out.println("inside setupdriver"+browser);
		//browsertype = browser;
			//getchromeDriver();
		//}
		}

	
	public  static void tearDown() {
		if(getDriver()!=null) {
			LoggerLoad.info("enter teardown");
			getDriver().close();
			getDriver().quit();
			driver=null;
		}
		
		
	}
	
	public void closedriver() {
		driver.close();
	}
	
	
}
