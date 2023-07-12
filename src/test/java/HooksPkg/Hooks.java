package HooksPkg;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driverFactory.DriverSetup;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.Configreader;
import utils.LoggerLoad;

public class Hooks {
	private static WebDriver driver;
	 private static DriverSetup driversetup;
	 
		@BeforeAll
		public static void before() throws Throwable
		{
			System.out.println("enter hooks beforeall");
			//String browser	=Configreader.loadConfig(); //this is  for junit
			String browser=	Configreader.getBrowserType(); // this is for cross browser TestNG runner
			driversetup=new DriverSetup();
			driver=driversetup.getdesireDriver(browser);
			
		}
		
			 
		 @AfterStep
		    public void tearDown(Scenario scenario) {
			 LoggerLoad.info("enter after hook");
		        if(scenario.isFailed()) {
		            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		            scenario.attach(screenshot, "image/png", scenario.getName()); 
		            //driversetup.tearDown();
		        } 
		        //driversetup.getDriver().quit();
		        //driversetup.tearDown();
		    }
		 @AfterAll
		 public static void closedriver( ) {
			 driversetup.closedriver();
		 }
}
