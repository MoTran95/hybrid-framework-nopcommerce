package com.wordpress.admin;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.wordpress.AdminDashboadPageObject;
import pageObject.wordpress.AdminLoginPageObject;
import pageObject.wordpress.AdminPostAddNewPageObject;
import pageObject.wordpress.AdminPostSearchPageObject;
import pageObject.wordpress.PageGeneratorManager;
import pageObject.wordpress.UserHomePO;
import pageObject.wordpress.UserPostDetailPO;
import pageObject.wordpress.UserSearchPostPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	String adminUserName = "automationfc";
	String adminPassword = "automationfc";
	String searchPostUrl;
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Coding Title " + randomNumber;
	String postBody= "Live Coding Body " + randomNumber;
	String editPostTitle = "Edit title " + randomNumber;
	String editPostBody = "Edit body " + randomNumber;
	String authorName = "automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentToday();

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and admin Url");
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriver(browserName, this.adminUrl);

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUserName);
		adminLoginPage.enterToUsernameTextbox(adminUserName);

		log.info("Pre-Condition - Step 03: Enter to Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-Condition - Step 04: Click to Login Button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();

	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		log.info("Create_Post - Step 02: Click to 'Add New' button");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton(); 

		log.info("Create_Post - Step 03: Enter to Post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Create_Post - Step 04: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);

		log.info("Create_Post - Step 05: Click to publish button ");
		adminPostAddNewPage.clickToPublishButtonOrUpdateButton("Publish");
		
		log.info("Create_Post - Step 06: Click to pre-publish button ");
		adminPostAddNewPage.clickToPrePublishButton();

		log.info("Create_Post - Step 07: Verify post published message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}

	@Test
	public void Post_02_Search_Post_And_View() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Search_Post - Step 04: Verify search table contains '" + postTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title",postTitle));
		
		log.info("Search_Post - Step 05: Verify search table contains '" + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author",authorName));
		
		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithCurrentDay(postTitle, currentDay));
		
		log.info("Search_Post - Step 08: Click to post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithBody(postTitle,  postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithAuthorName(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithCurrentDay(postTitle,currentDay));

	}


	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin Site");
		adminDashboardPage	= userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - step 05: Click to Post title link and navigate to Edit Post Page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Enter to Post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);

		log.info("Edit_Post - Step 07: Enter to body");
		adminPostAddNewPage.enterToEditPostBody(editPostBody);

		log.info("Edit_Post - Step 08: Click to update button ");
		adminPostAddNewPage.clickToPublishButtonOrUpdateButton("Update");
		
		log.info("Edit_Post - Step 09: Verify post update message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
		
		log.info("Edit_Post - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Edit_Post - Step 11: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Edit_Post - Step 12: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 13: Verify search table contains '" + editPostTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title",editPostTitle));
		
		log.info("Edit_Post - Step 14: Verify search table contains '" + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author",authorName));
		
		log.info("Edit_Post - Step 15: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Edit_Post - Step 16: Verify Post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithCurrentDay(editPostTitle, currentDay));
		
		log.info("Edit_Post - Step 17: Click to post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
		
		log.info("Edit_Post - Step 18: Verify Post infor displayed at Post detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithBody(editPostTitle,  editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithAuthorName(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithCurrentDay(editPostTitle,currentDay));
	}

	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin Site");
		adminDashboardPage	= userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 04: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - step 05: Select Post Detail checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
		
		log.info("Delete_Post - step 06: Select Move to trash item in dropdown");
		adminPostSearchPage.selectItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - step 07: Click to apply button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete_Post - step 08: Verify 1 post moved to the Trash message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplay("1 post moved to the Trash."));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Delete_Post - Step 10: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - step 11: Verify 'No posts found' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplay("No posts found."));
		
		log.info("Delete_Post - Step 12: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home Page");
		verifyTrue(userHomePage.isPostInforUnDisplayedWithTitle(editPostTitle));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to Search Posts button");
		userSearchPostPage = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found."));
		


		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	AdminLoginPageObject adminLoginPage;
	AdminDashboadPageObject adminDashboardPage;
	AdminPostSearchPageObject adminPostSearchPage;
	AdminPostAddNewPageObject adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;
}
