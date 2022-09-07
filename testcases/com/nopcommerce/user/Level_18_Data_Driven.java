package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import pageObjects.nopcommerce.user.UserRegisterPageObject;
import pageUIs.nopcommerce.user.BasePageUI;
import utilities.DataHelper;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_18_Data_Driven extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMapper.getUserData();
		email = userData.getEmailAddress()  + generateFakeNumber() + "@hotmail.com";
		System.out.println(userData.getSubjects().get(0).getName());
		System.out.println(userData.getSubjects().get(1).getName());

	}

	@Test
	public void User_01_Register() {
		log.info("Register - step 01: open register page");
		registerPage = homePage.openRegisterPage();
		
		registerPage.clickToRadioButtonByLabel(driver,"Female");
		log.info("Register - step 02: enter to firstname textbox with value is '" + userData.getFirstName() + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", userData.getFirstName());

		log.info("Register - step 03: enter to lastName textbox with value is '" + userData.getLastName() + "'");
		registerPage.inputToTextboxByID(driver, "LastName", userData.getLastName());

		log.info("Register - step 04: enter to email textbox with value is '" + email + "'");
		registerPage.inputToTextboxByID(driver, "Email", email);
		
		registerPage.clickToCheckboxByLabel(driver,"Newsletter");


		log.info("Register - step 05: enter to password textbox with value is '" + userData.getPassword() + "'");
		registerPage.inputToTextboxByID(driver, "Password", userData.getPassword());
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", userData.getDate());
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", userData.getMonth());
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", userData.getYear());

		log.info("Register - step 06: enter to password textbox with value is '" + userData.getPassword() + "'");

		registerPage.inputToTextboxByID(driver, "ConfirmPassword", userData.getPassword());
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
		log.info("Login - step 03: enter password to textbox with value is '" + userData.getPassword() + "'");
		loginPage.inputToTextboxByID(driver, "Password", userData.getPassword());

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
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"FirstName"), userData.getFirstName());
		log.info("My Account - step 04: Verify 'Last Name' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"LastName"), userData.getLastName());
		log.info("My Account - step 05: Verify 'Email' value is correctly");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver,"Email"), email);


	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private String email;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	UserDataMapper userData;


}
