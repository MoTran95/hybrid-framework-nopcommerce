package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import pageObjects.nopcommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_14_ExtendV5_Screenshot extends BaseTest {
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
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		ExtentTestManager.getTest().log(Status.INFO,"Register - step 02: enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 03: enter to lastName textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 04: enter to email textbox with value is '" + email + "'");
		registerPage.inputToEmailTextbox(email);

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 05: enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 06: enter to password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 07: click register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO,"Register - step 08: verify register success message is displayed ");
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration ");
		

	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login with valid email and password");
		ExtentTestManager.getTest().log(Status.INFO,"Register - step 00: click logout button");
		homePage = registerPage.clickToLogoutLink();
		ExtentTestManager.getTest().log(Status.INFO,"Login - step 01: open login page");
		loginPage = homePage.openLoginPage();
		ExtentTestManager.getTest().log(Status.INFO,"Login - step 02: enter email to textbox with value is '" + email + "'");
		loginPage.inputToEmailTextbox(email);
		ExtentTestManager.getTest().log(Status.INFO,"Login - step 03: enter password to textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO,"Login - step 04: click login button");
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
