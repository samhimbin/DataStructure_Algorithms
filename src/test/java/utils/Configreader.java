package utils;

import java.util.ResourceBundle;

public class Configreader {
private static String browserType = null;
	
	public static void setBrowserType(String browser) {
		System.out.println("enter config reader set browsertype " + browser);
		browserType = browser;
		System.out.println(" go to hooks");
	}
	
	public static String getBrowserType()throws Throwable{
		if(browserType!=null)
			return browserType;
		else
			throw new RuntimeException("browser not specified in the testng.xml");		
	}
	public static String loadConfig() {
		 ResourceBundle rb;
		 String br;
		rb=ResourceBundle.getBundle("Config");
		return br=rb.getString("browser");
		
	}
}
