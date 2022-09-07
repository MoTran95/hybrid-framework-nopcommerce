package com.sauce.sort;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGeneratorManager;
import pageObjects.sauceLab.ProductPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_18_Sort_Asc_Desc extends BaseTest {
	@Parameters({"browser","appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		productPage =	loginPage.clickToLoginButton();
	}
	@Test
	public void Sort_01_Name() {
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(0);
		Assert.assertTrue(productPage.isProductNameSortByAscending());
		
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		Assert.assertTrue(productPage.isProductNameSortByDecsending2());

	}
	@Test
	public void Sort_02_Price() {
		productPage.selectItemInProductSortDropdown("Price (low to high)");
		Assert.assertTrue(productPage.isProductPriceSortByAscending());

		
		productPage.selectItemInProductSortDropdown("Price (high to low)");
		Assert.assertTrue(productPage.isProductPriceSortByDecsending());

	}


	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;

}
