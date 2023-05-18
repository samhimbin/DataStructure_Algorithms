package stepdefinitions;

import org.junit.Assert;

import driverFactory.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PortalPage;
import utils.LoggerLoad;

public class PortalSteps extends BaseClass{

	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		portalpage = new PortalPage(DriverSetup.getDriver());
		LoggerLoad.info("launch browser");
	    }

	@Given("User opens dsalgo portal app url")
	public void user_opens_dsalgo_portal_app_url() {
	   DriverSetup.openPage("https://dsportalapp.herokuapp.com");
	   LoggerLoad.info("Portal opened");
	}

	@When("User click on Get Started button")
	public void user_click_on_get_started_button() {
		portalpage.clickgetstartedbtn();
		LoggerLoad.info("Clicked Getstarted button");
	}

	@Then("User is navigated to Home page")
	public void user_is_navigated_to_home_page() {
		String title=DriverSetup.getdriverTitle();
		String Expectedtitle=portalpage.gethomepagetitle();
		Assert.assertEquals(Expectedtitle,title);
		LoggerLoad.info("user navigated to Homepage");
		
	    	}

}
