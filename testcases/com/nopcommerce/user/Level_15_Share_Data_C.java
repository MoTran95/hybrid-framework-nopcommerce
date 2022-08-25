package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.commom.Commom_01_Register_Cookie;
import com.nopcommerce.commom.Commom_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;

import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_15_Share_Data_C extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		email = Commom_01_Register_End_User.email;
		password = Commom_01_Register_End_User.password;
		log.info("Pre-condition - step 01: open login page");
		loginPage = homePage.openLoginPage();
		log.info("Pre-condition - step 02: Set cookie and reload page ");
		loginPage.setCookies(driver, Commom_01_Register_Cookie.loggedCookies);
		loginPage.refreshPage(driver);
		log.info("Pre-condition - step 03: Verify My Account link is displayed ");
		verifyTrue(homePage.isMyAccountLinkDisplayed());


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
	private String email, password;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;

}
