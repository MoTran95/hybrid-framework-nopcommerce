package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_01_Register extends BasePage {
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homepage;
	private UserRegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homepage = new UserHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "John" + generateFakeNumber() + "@hotmail.com";
	
	}

	@Test
	public void Register_01_Register_Empty_Data() {

		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Register_Invalid_Email() {

		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@334$55");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Register_03_Register_Success() {

		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();

	}

	@Test
	public void Register_04_Register_Existing_Email() {

		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Register_Password_Less_Than_6_Chars() {
		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Register_Invalid_Confirm_Password() {
		homepage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}


}
