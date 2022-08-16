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

public class Level_14_Allure extends BaseTest {
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
		
		registerPage = homePage.openRegisterPage();
		
		registerPage.inputToFirstNameTextbox(firstName);

		
		registerPage.inputToLastNameTextbox(lastName);

		
		registerPage.inputToEmailTextbox(email);

		
		registerPage.inputToPasswordTextbox(password);

		
		registerPage.inputToConfirmPasswordTextbox(password);

	
		registerPage.clickToRegisterButton();


		Assert.assertEquals(registerPage.getRegisterSucessMessage(), "Your registration ");
		

	}

	@Test
	public void User_02_Login(Method method) {

		homePage = registerPage.clickToLogoutLink();
		
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(email);
		
		loginPage.inputToPasswordTextbox(password);

		
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
