package com.facebook.register;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class Level_12_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
	}

	@Test
	public void TC_02_Verify_Element_UnDisplayed_In_Dom() {

		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
	}

	@Test
	public void TC_03_Verify_Element_UnDisplayed_Not_In_Dom() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
}
