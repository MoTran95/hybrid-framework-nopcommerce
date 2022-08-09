package com.jquery.datatable;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);

	}


	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("18"));


	}


	public void Table_02_Enter_To_Header() {
		homePage.refreshPage(driver);
		homePage.enterToHeaderTextboxByLabel("Country","Argentina");
		homePage.enterToHeaderTextboxByLabel("Females","338282");
		homePage.enterToHeaderTextboxByLabel("Males","349238");
		homePage.enterToHeaderTextboxByLabel("Total","687522");
		homePage.sleepInSecond(3);
	}

	@Test
	public void Table_03() {
		expectedAllCountryValues = homePage.readFile(GlobalConstants.COUNTRY_DATA + "country.txt");
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	

}
