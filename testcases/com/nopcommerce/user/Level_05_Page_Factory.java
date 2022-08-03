package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.LoginPageObject;
import pageFactory.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_05_Page_Factory extends BaseTest {
	private WebDriver driver;
	private String validEmail, invalidEmail, notFoundEmail, firstName, lastName, validPassword, invalidPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "FC@3444.@nn6vn";
		notFoundEmail = "John" + generateFakeNumber() + "@hotmail.vn";
		validPassword = "123456";
		invalidPassword = "654321";
		validEmail = "John" + generateFakeNumber() + "@hotmail.com";
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(validPassword);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
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
