package com.jquery;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

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

	public void Table_03() {
		expectedAllCountryValues = homePage.readFile(GlobalConstants.COUNTRY_DATA + "country.txt");
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	@Test
	public void Table_04_Action_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.enterToTextboxByColumnNameAtRowNumber("Album","2","Michael 97");
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist","4","Michael Jackson");
		homePage.enterToTextboxByColumnNameAtRowNumber("Year","3","1997");
		homePage.enterToTextboxByColumnNameAtRowNumber("Price","1","150");
		homePage.selectDropdownByColumnNameAtRowNumber("Origin","5","Japan");
		homePage.selectDropdownByColumnNameAtRowNumber("Origin","1","US");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?","3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?","5");
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?","1");
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?","2");
		homePage.unCheckToCheckboxByColumnNameAtRowNumber("With Poster?","4");
		homePage.clickToIconByRowNumber("1","Remove Current Row");
		homePage.clickToIconByRowNumber("1","Insert Row Above");
		homePage.clickToIconByRowNumber("3","Move Up");
		homePage.clickToIconByRowNumber("5","Remove Current Row");
		homePage.clickToIconByRowNumber("4","Remove Current Row");
		homePage.clickToIconByRowNumber("3","Remove Current Row");
		homePage.clickToIconByRowNumber("2","Remove Current Row");
		homePage.clickToIconByRowNumber("1","Remove Current Row");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	

}
