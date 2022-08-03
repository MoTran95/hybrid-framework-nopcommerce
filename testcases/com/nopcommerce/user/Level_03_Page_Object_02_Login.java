package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_02_Login extends BasePage {
	private WebDriver driver;
	private String validEmail, invalidEmail, notFoundEmail, firstName, lastName, validPassword, invalidPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "FC@3444.@nn6vn";
		notFoundEmail = "John" + generateFakeNumber() + "@hotmail.vn";
		validPassword = "123456";
		invalidPassword = "654321";
		validEmail = "John" + generateFakeNumber() + "@hotmail.com";
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
		homePage = new UserHomePageObject(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		homePage.openLoginPage();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(validPassword);
		loginPage.clickToLoginButton();
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
