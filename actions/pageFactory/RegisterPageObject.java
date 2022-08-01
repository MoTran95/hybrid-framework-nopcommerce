package pageFactory;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
}
