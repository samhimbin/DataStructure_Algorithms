package testRunner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.Configreader;

@CucumberOptions(
		plugin = {"pretty", "html:target/NewDs-algo.html",  //cucumber reports
				//"json:target/NewDs-algo.json",
				//"rerun:target/rerun.txt", //mandatory for capture failures 
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", //reporting purpose-- html reports are created
		},
				monochrome=true,  //console output color and eliminate junk characters
		//dryRun=false, // (=true)->compilation purpose mostly to chk stepdefinitions are implemented or not
		
	    tags="@portal or @home or @register or @login or @dstruct or @arry or @llist",
		features = {"src/test/resources/features"}, //location of feature files, we can give this way-->".//features//"
		glue= {"stepdefinitions","HooksPkg"}) //location of step definition files, gives granural level of info in test result
@Test
public class TestNGRunner extends AbstractTestNGCucumberTests{
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	@BeforeTest
	@Parameters({"browser"})
	public void definebrowser(@Optional String browser) throws Throwable {
		System.out.println("enter definebrowser");
		System.out.println(browser);
		Configreader.setBrowserType(browser);
	}
}
