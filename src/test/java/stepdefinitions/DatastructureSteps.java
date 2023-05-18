package stepdefinitions;

import org.openqa.selenium.WebDriver;

import driverFactory.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.DatastructurePage;

public class DatastructureSteps extends BaseClass{

	WebDriver driver = DriverSetup.getDriver();
	DatastructurePage datastructpage =new DatastructurePage(driver);
	@Given("User click on Get started button in data Structures module")
	public void user_click_on_get_started_button_in_data_structures_module() {
		
		datastructpage.clickGetStarted();
	}

	@When("User click on Time Complexity button")
	public void user_click_on_time_complexity_button() {
		datastructpage.selectTimecomplexity();
	}

	@Given("User click on practice questons")
	public void user_click_on_practice_questons() {
		datastructpage.practicequestions();
	}

	@Given("Navigate back to data stuctures introduction page")
	public void navigate_back_to_data_stuctures_introduction_page() {
	   driver.navigate().back();
	}
}
