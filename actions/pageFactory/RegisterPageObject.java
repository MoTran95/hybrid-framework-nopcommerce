package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmationTextbox;
	
	@CacheLookup
	@FindBy(xpath = "//button[@id ='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameError;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameError;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailError;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordError;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmationNameError;
	
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath = "//a[@class ='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']//li")
	private WebElement existingEmailErrorMessage;
	

	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, firstNameError);
		return getElementText(driver,firstNameError);
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, lastNameError);
		return getElementText(driver,lastNameError);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailError);
		return getElementText(driver,emailError);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordError);
		return getElementText(driver,passwordError);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmationNameError);
		return getElementText(driver,confirmationNameError);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeyToElement(driver, firstNameTextbox, firstName);
		
	}

	public void inputToLastNameTextbox(String lastname) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeyToElement(driver, lastNameTextbox, lastname);
		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmationTextbox);
		sendKeyToElement(driver, confirmationTextbox,confirmPassword );
		
	}

	public String getRegisterSucessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver,registerSuccessMessage);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
		
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver,existingEmailErrorMessage);
	}
	
}
