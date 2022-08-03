package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.FISRT_NAME_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.FISRT_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void inputToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);
		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,confirmPassword );
		
	}

	public String getRegisterSucessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
		
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver,UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	
}
