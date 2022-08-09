package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Switch_Role extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userPassword = "123456";
		userEmailAddress = "John33233@hotmail.com";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";

	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		userCustomerInforPage = userHomePage.openMyAccountPage();
		userHomePage = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_DEV_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboard = adminLoginPage.loginAsAmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboard.isDashboardHeaderDisplayed());
		
		adminLoginPage = adminDashboard.clickToLogoutLinkAtAdminPage(driver);
		adminDashboard.sleepInSecond(3);
		
		
	}

	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_DEV_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String userEmailAddress, userPassword,adminEmailAddress, adminPassword;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboard;
	private UserCustomerInforPageObject userCustomerInforPage;

}
