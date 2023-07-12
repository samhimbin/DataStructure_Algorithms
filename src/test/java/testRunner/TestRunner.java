package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/ds_algo.html",
                            "json:target/Ds-algo.json",
                            "rerun:target/rerun.txt"  ,//mandatory for capture failures 
                            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                            "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
							monochrome=true,
							//tags="@portal or @home or @register or @login or @dstruct or @arry or @llist",
									tags="@portal or @home",
							features="src/test/resources/features",
							glue = {"stepdefinitions","HooksPkg"})

public class TestRunner {

}

