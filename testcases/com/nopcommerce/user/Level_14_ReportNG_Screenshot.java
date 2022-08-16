package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_14_ReportNG_Screenshot extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		email = "John" + generateFakeNumber() + "@hotmail.com";

	}

	@Test
	public void User_01_Register() {
		log.info("Register - step 01: open register page");
		registerPage = homePage.openRegisterPage();
		log.info("Register - step 02: enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("Register - step 03: enter to lastName textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		log.info("Register - step 04: enter to email textbox with value is '" + email + "'");
		registerPage.inputToEmailTextbox(email);

		log.info("Register - step 05: enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register - step 06: enter to password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register - step 07: click register button");
		registerPage.clickToRegisterButton();

		log.info("Register - step 08: verify register success message is displayed ");
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration ");
		

	}

	@Test
	public void User_02_Login() {
		log.info("Register - step 09: click logout button");
		homePage = registerPage.clickToLogoutLink();
		log.info("Login - step 01: open login page");
		loginPage = homePage.openLoginPage();
		log.info("Login - step 02: enter email to textbox with value is '" + email + "'");
		loginPage.inputToEmailTextbox(email);
		log.info("Login - step 03: enter password to textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Login - step 04: click login button");
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String email, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

}
