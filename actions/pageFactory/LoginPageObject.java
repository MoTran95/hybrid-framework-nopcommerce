package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopcommerce.user.UserLoginPageUI;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement email_error_message;

	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement unsuccessfulErrorMessage;

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		return new HomePageObject(driver);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);

	}

	public String getErrorMessageAtUnsuccessfull() {
		waitForElementVisible(driver, unsuccessfulErrorMessage);
		return getElementText(driver, unsuccessfulErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, email_error_message);
		return getElementText(driver, email_error_message);

	}

}
