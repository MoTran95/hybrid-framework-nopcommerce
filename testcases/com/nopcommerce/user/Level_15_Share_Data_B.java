package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.commom.Commom_01_Register_End_User;

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

public class Level_15_Share_Data_B extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
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

	}

	@Test
	public void Search_01_Empty_Data() {

	}

	@Test
	public void Search_02_Relative_Product_Name() {

	}

	@Test
	public void Search_03_Absolute_Product_Name() {

	}

	@Test
	public void Search_04_Parent_Category() {

	}

	@Test
	public void Search_05_Incorrect_Manufactorer() {

	}

	@Test
	public void Search_06_Correct_Manufactorer() {

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
