package com.jquery;

import commons.BaseTest;
import pageObjects.jQuery.uploadFiles.HomePageObject;
import pageObjects.jQuery.uploadFiles.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_UploadFiles extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);

	}
	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, file1);
		Assert.assertTrue(homePage.isFileLoadedByName(file1));
		homePage.clickToStartButton();
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(file1));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(file1));
	}
	@Test
	public void Upload_02_Multiple_Files_Per_Time() {
		homePage.refreshPage(driver);
		homePage.uploadMultipleFiles(driver, fileNames);
		Assert.assertTrue(homePage.isFileLoadedByName(file1));
		Assert.assertTrue(homePage.isFileLoadedByName(file2));
		Assert.assertTrue(homePage.isFileLoadedByName(file3));
		homePage.clickToStartButton();
		for (String filename : fileNames) {
			Assert.assertTrue(homePage.isFileLinkUpLoadedByName(filename));
			homePage.sleepInSecond(2);
			Assert.assertTrue(homePage.isFileImageUpLoadedByName(filename));
			homePage.sleepInSecond(1);
		}
		
	}
	@Test
	public void Table_03() {

	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	String file1 = "01.jpg";
	String file2 = "02.jpg";
	String file3 = "03.jpg";
	String [] fileNames = {"01.jpg","02.jpg", "03.jpg"};

}
