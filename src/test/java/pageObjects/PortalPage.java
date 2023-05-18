package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepdefinitions.BaseClass;

public class PortalPage extends BaseClass {

	public PortalPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Get Started']")
	WebElement getstartedbtn;

	public void clickgetstartedbtn() {
		getstartedbtn.click();
	}

	public String gethomepagetitle() {
		return "NumpyNinja";
	}
}
