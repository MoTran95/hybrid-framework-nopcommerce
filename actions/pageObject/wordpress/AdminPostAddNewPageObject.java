package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPageObject extends BasePage {
	WebDriver driver;

	public AdminPostAddNewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitleValue) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
		sendKeyToElement(driver,AdminPostAddNewPageUI.TITLE_TEXTBOX ,postTitleValue);
		
	}

	public void enterToAddNewPostBody(String postBodyValue) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sendKeyToElement(driver,AdminPostAddNewPageUI.BODY_TEXTBOX ,postBodyValue);

	}
	public void enterToEditPostBody(String postBodyValue) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sendKeyToElement(driver,AdminPostAddNewPageUI.BODY_TEXTBOX ,postBodyValue);

	}

	public void clickToPublishButtonOrUpdateButton(String value) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON_OR_UPDATE_BUTTON,value);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON_OR_UPDATE_BUTTON,value);
	}

	public boolean isPostPublishMessageDisplayed(String postPublishedMessage) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISH_MESSAGE_OR_UPDATED_MESSAGE, postPublishedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISH_MESSAGE_OR_UPDATED_MESSAGE, postPublishedMessage);
	}

	public AdminPostSearchPageObject openSearchPostPageUrl(String searchPostUrl) {
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManager.getAdminPostSearchPage(driver);
	}

	public void clickToPrePublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		
	}
}
