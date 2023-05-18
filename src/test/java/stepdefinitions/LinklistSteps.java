package stepdefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LinkedlistPage;
import pageObjects.LoginPage;
import utils.LoggerLoad;
import utils.ExcelReader;

public class LinklistSteps extends BaseClass{

WebDriver driver = DriverSetup.getDriver();
	
	String excelpath = ".\\src/test/resources/ExcelData/pythoncodeData.xlsx";

	String expmsg;

	@Given("The user is on signIn Page of DSAlgo portal")
	public void the_user_is_on_sign_in_page_of_ds_algo_portal() {

		loginpage = new LoginPage(driver);
		DriverSetup.openPage("https://dsportalapp.herokuapp.com/login");
	}

	@When("user enter valid username {string} and password {string}")
	public void user_enter_valid_username_and_password(String uname, String password) {
		loginpage.enternamepasswrd(uname, password);
	}

	@Then("user click on login button")
	public void user_click_on_login_button() {
		loginpage.loginbtnclk();
	}

	@Given("The user is in the Home page after logged in")
	public void the_user_is_in_the_home_page_after_logged_in() {
		LoggerLoad.info("In HomePage");
	}

	@When("The user selecting linkedlist item from the drop down menu")
	public void the_user_selecting_linkedlist_item_from_the_drop_down_menu() {
		homepage = new HomePage(driver);
		homepage.dropDownclk();
		LoggerLoad.info("Dropdown got clicked");
		homepage.pickLinkedlist();
	}

	@Then("The user should be directed to Linked List Page")
	public void the_user_should_be_directed_to_linked_list_page() {
		LoggerLoad.info("user is in Linked list page");

	}

	@And("The user clicks on {string} link")
	public void the_user_clicks_on_link(String string) {
		linklistpage = new LinkedlistPage(driver);
		linklistpage.introOfLinkedlist();
		LoggerLoad.info(string + " link got clicked");
	}

	@When("The user clicks {string} button in the {string} page")
	public void the_user_clicks_button_in_the_page(String TryHere, String introduction) {
		linklistpage.tryHereLink();
		LoggerLoad.info(TryHere + " got clicked on " + introduction + " page");
	}

	@Then("The user should be redirected to a page having an tryEditor with a Run button to test")
	public void the_user_should_be_redirected_to_a_page_having_an_try_editor_with_a_run_button_to_test() {
		linklistpage.codeMirrorText();
		linklistpage.clickRunButton();
		String res = linklistpage.codeOutput();
		System.out.println("result = " + res);
	}

	@And("user will navigate back and returns to tryEditor to give invalid python code")
	public void user_will_navigate_back_and_returns_to_try_editor_to_give_invalid_python_code() {
		DriverSetup.NavBack();
		linklistpage.tryHereLink();
		linklistpage.invalidPythonCode();
		linklistpage.clickRunButton();

	}

	@Then("user will get alert message and accept it")
	public void user_will_get_alert_message_and_accept_it() {
		Alert alt = driver.switchTo().alert();
		alt.accept();
		DriverSetup.NavBack();
		

	}

	@Given("The user is in a try here page having  tryEditor with a Run button to test")
	public void the_user_is_in_a_try_here_page_having_try_editor_with_a_run_button_to_test() {
		LoggerLoad.info("In tryEditor page");
		linklistpage = new LinkedlistPage(driver);
	}

	@And("user clicks on tryHere button")
	public void user_clicks_on_try_here_button() {
		linklistpage.tryHereLink();
	}

	@When("The user Enter valid python code in tryEditor from sheet {string} and {int}")
	public void the_user_enter_valid_python_code_in_try_editor_from_sheet_and(String string, Integer row)
			throws InvalidFormatException, IOException {
		ExcelReader reader = new ExcelReader();
		LoggerLoad.info("User is in TryEditor page");
		List<Map<String, String>> testData = reader.getData(excelpath, "Sheet1");
		String pyCode = testData.get(row).get("pythoncode"); // Column heading
		String output = testData.get(row).get("result"); // Column heading
		expmsg = output;
		
		linklistpage.excelPythonCode(pyCode, output);
		LoggerLoad.info("python code being sent");

	}

	@When("user click on Run button")
	public void user_click_on_run_button() {
		linklistpage.clickRunButton();

	}

	@Then("The user should be presented with the Run output")
	public void the_user_should_be_presented_with_the_run_output() {
		String res = linklistpage.codeOutput();
		System.out.println("result = " + res);
		DriverSetup.NavBack();

	}

	@When("The user Enter invalid python code in tryEditor from sheet {string} and {int}")
	public void the_user_enter_invalid_python_code_in_try_editor_from_sheet_and(String string, Integer row)
			throws InvalidFormatException, IOException {
		ExcelReader reader = new ExcelReader();
		LoggerLoad.info("User is in TryEditor page");
		List<Map<String, String>> testData = reader.getData(excelpath, "Sheet1");
		String pyCode = testData.get(row).get("pythoncode"); // Column heading
		String output = testData.get(row).get("result"); // Column heading
		expmsg = output;
		
		linklistpage.excelPythonCode(pyCode, output);
		LoggerLoad.info("python code being sent");
	}

	@Then("The user should get Alert message")
	public void the_user_should_get_alert_message() {
		
		Alert alert = driver.switchTo().alert();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		if (w.until(ExpectedConditions.alertIsPresent()) == null)
			System.out.println("alert not exist");
		else
			System.out.println("Alert exists");
            alert.accept();
            DriverSetup.NavBack();
	}
	@Given("The user is in Linked List page")
	public void the_user_is_in_linked_list_page() {
		linklistpage= new LinkedlistPage(driver);
	    
	}
	@When("The user clicks the {string} a Linked List button")
	public void the_user_clicks_the_a_linked_list_button(String string) {
		linklistpage.createLnkList();
		LoggerLoad.info("User is ready to create Linked list");
	}
	
	@Then("The user should be redirected to tryEditor page with a Run button to test")
	public void the_user_should_be_redirected_to_try_editor_page_with_a_run_button_to_test() {
		linklistpage.tryHereLink();
	}
	
	@When("The user clicks the {string} Linked List hyperlink")
	public void the_user_clicks_the_linked_list_hyperlink(String string) {
		linklistpage.typesOfLinkedList();
	    LoggerLoad.info("user clicked on types of linked list link");
	}
	@When("The user clicks the {string} link")
	public void the_user_clicks_the_link(String string) {
		linklistpage.implementLinkedlist();
	    LoggerLoad.info("User can implement linkedlist" );
	}
	@When("The user clicks {string} link")
	public void the_user_clicks_link(String string) {
		linklistpage.traversalLink();
	   LoggerLoad.info("user is in Traversal Linked list page");
	}
	@When("The user clicks on {string}")
	public void the_user_clicks_on(String string) {
		linklistpage.insertionLink();
	   LoggerLoad.info("User in insertion linked list page");
	}
	@When("The user clicks the {string} link of {string}")
	public void the_user_clicks_the_link_of(String string, String string2) {
		linklistpage.deletionLink();
	   LoggerLoad.info("User is in deletion of Linked list");
	}
	@When("The user clicks on to the {string} link")
	public void the_user_clicks_on_to_the_link(String string) {
		linklistpage.practiceQuestionsLink();
	    LoggerLoad.info("User is in Practice Questions Page");
	}

	@Then("The user should be directed to Practice Questions of Linked List Page")
	public void the_user_should_be_directed_to_practice_questions_of_linked_list_page() {
	    DriverSetup.NavBack();
	    LoggerLoad.info("User been Navigated back to Main page of LinkedList");
	    linklistpage.clickSignOut();
	    //DriverSetup.tearDown();
	}

	
}
