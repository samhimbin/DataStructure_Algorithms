package stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import driverFactory.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import utils.LoggerLoad;

public class LoginDataTableSteps extends BaseClass{
	WebDriver driver = DriverSetup.getDriver();

	@Given("user is in homepage of dsalgo website")
	public void user_is_in_homepage_of_dsalgo_website() {
		loginpage = new LoginPage(driver);
		DriverSetup.openPage("https://dsportalapp.herokuapp.com/login");
	}

	@When("usr clicks on the login link to give username and password")
	public void usr_clicks_on_the_login_link_to_give_username_and_password() {
		loginpage.clkSignIn();
	}

	@Then("user sign up with following details")
	public void user_sign_up_with_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<List<String>> object = dataTable.asLists();
		        
		
         
				loginpage.enternamepasswrd(object.get(0).get(0), object.get(0).get(1));
				String status = object.get(0).get(2);
				System.out.println(status);
				Thread.sleep(3000);
				if (status.equalsIgnoreCase("valid")) {
					loginpage.loginbtnclk();
				} else {
					LoggerLoad.info("invalid credentials");
				}
			}
}
