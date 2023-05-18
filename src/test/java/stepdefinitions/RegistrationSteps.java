package stepdefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import utils.LoggerLoad;
import utils.ExcelReader;


public class RegistrationSteps extends BaseClass{

	WebDriver driver= DriverSetup.getDriver();
	String excelpath = ".\\src\\test\\resources\\ExcelData\\registrationExceldata.xlsx";
	String expmsg = "";
	int rowval = 0;
	
	@Given("user click registration link")
	public void user_click_registration_link() throws InterruptedException {
		registerpage = new RegisterPage(driver);
		loginpage = new LoginPage(driver);
		registerpage.registrationlink();
	}

	@When("user enter username,password,confirm password from given {string} and {int}")
	public void user_enter_username_password_confirm_password_from_given_and(String string, Integer int1) throws InvalidFormatException, IOException, InterruptedException {
		ExcelReader reader = new ExcelReader();
		LoggerLoad.info("User enter registration credentials");
		List<Map<String, String>> testData = reader.getData(excelpath, "Sheet1");
		String User_name = testData.get(int1).get("username"); // Column heading
		String Pass_word = testData.get(int1).get("password"); // Column heading
		String Pass_wordcnf = testData.get(int1).get("passwordconfirm");
		expmsg = testData.get(int1).get("expectedmsg");
		registerpage.readusernameandpassword(User_name, Pass_word, Pass_wordcnf);
		LoggerLoad.info("Registration credentials entered");
	    rowval=int1;
	}

	@Then("user click register button with expected message")
	public void user_click_register_button_with_expected_message() {
		registerpage.regbtnclk();
		String actmsg = "";

		if (rowval <= 2) { //row of excel begin at '0' --> as it is converted into List
			WebElement hidtext = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='username']"))));
			System.out.println(hidtext.getAttribute("validationMessage"));
			LoggerLoad.info(hidtext.getAttribute("validationMessage"));
			actmsg = hidtext.getAttribute("validationMessage");
			//Assert.assertEquals(expmsg,actmsg);
		
			//assertEquals(actmsg,expmsg);

		} 
		else if ((rowval > 2) && (rowval < 10)) {
			
			actmsg = registerpage.alertdismsg();
			//Assert.assertEquals(expmsg,actmsg);
			//assertEquals(actmsg,expmsg);

		}
		 else if(rowval==10) {

		actmsg = loginpage.alertdismsg();
		System.out.print(actmsg);
		// Assert.assertEquals(actmsg, expmsg);
		
		loginpage.alertdismsg();
		loginpage.clkSignIn();
		 }

	}
}
