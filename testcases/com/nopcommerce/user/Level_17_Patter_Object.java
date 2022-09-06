package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import pageObjects.nopcommerce.user.UserRegisterPageObject;
import pageUIs.nopcommerce.user.BasePageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_17_Patter_Object extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		email = "John" + generateFakeNumber() + "@hotmail.com";
		date = "10";
		month = "May";
		year = "1998";

	}

	@Test
	public void User_01_Register() {
		log.info("Register - step 01: open register page");
		registerPage = homePage.openRegisterPage();
		
		registerPage.clickToRadioButtonByLabel(driver,"Female");
		log.info("Register - step 02: enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Register - step 03: enter to lastName textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("Register - step 04: enter to email textbox with value is '" + email + "'");
		registerPage.inputToTextboxByID(driver, "Email", email);
		
		registerPage.clickToCheckboxByLabel(driver,"Newsletter");


		log.info("Register - step 05: enter to password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

		log.info("Register - step 06: enter to password textbox with value is '" + password + "'");

		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		log.info("Register - step 07: click register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register - step 08: verify register success message is displayed ");
		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration completed");

	}

	@Test
	public void User_02_Login() {
		log.info("Register - step 09: click logout button");
		homePage = registerPage.clickToLogoutLink();
		log.info("Login - step 01: open login page");
		loginPage = homePage.openLoginPage();
		log.info("Login - step 02: enter email to textbox with value is '" + email + "'");
		loginPage.inputToTextboxByID(driver, "Email", email);
		log.info("Login - step 03: enter password to textbox with value is '" + password + "'");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Login - step 04: click login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Login - step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	@Test
	public void User_03_My_Account() {
		log.info("My Account - step 01: Navigate 'My Account' page");
		customerInforPage = homePage.openMyAccountPage();
		log.info("My Account - step 02: Verify 'Customer Page' link is displayed");
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		customerInforPage.sleepInSecond(10);
		log.info("My Account - step 03: Verify 'First Name' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"FirstName"), firstName);
		log.info("My Account - step 04: Verify 'Last Name' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"LastName"), lastName);
		log.info("My Account - step 05: Verify 'Email' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"Email"), email);


	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private String email, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private String date, month, year;

}
