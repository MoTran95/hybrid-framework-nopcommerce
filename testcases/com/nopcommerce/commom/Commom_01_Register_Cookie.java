package com.nopcommerce.commom;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Commom_01_Register_Cookie extends BaseTest {
	@Parameters("browser")
	@BeforeTest(description = "Create new commom User for all classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		email = "John" + generateFakeNumber() + "@hotmail.com";
		log.info("Pre-condition - step 01: open register page");
		registerPage = homePage.openRegisterPage();
		log.info("Pre-condition - step 02: enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("Pre-condition - step 03: enter to lastName textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);

		log.info("Pre-condition - step 04: enter to email textbox with value is '" + email + "'");
		registerPage.inputToEmailTextbox(email);

		log.info("Pre-condition - step 05: enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - step 06: enter to password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-condition - step 07: click register button");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - step 08: verify register success message is displayed ");
		verifyEquals(registerPage.getRegisterSucessMessage(), "Your registration completed");
		log.info("Pre-condition - step 09: click logout button");
		homePage = registerPage.clickToLogoutLink();
		log.info("Pre-condition - step 10: open login page");
		loginPage = homePage.openLoginPage();
		log.info("Pre-condition - step 11: enter email to textbox with value is '" + email + "'");
		loginPage.inputToEmailTextbox(email);
		log.info("Pre-condition - step 12: enter password to textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - step 13: click login button");
		homePage = loginPage.clickToLoginButton();
		loggedCookies = homePage.getAllCookies(driver);
	}
	@AfterTest
	public void afterClass() {
		driver.quit();
	}


	private WebDriver driver;
	private String firstName, lastName, password, email;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;

}
