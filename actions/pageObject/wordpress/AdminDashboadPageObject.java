package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboadPageObject extends BasePage {
	WebDriver driver;
   public AdminDashboadPageObject(WebDriver driver) {
		this.driver = driver;
	}
public AdminPostSearchPageObject clickToPostMenuLink() {
	waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
	clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
	return PageGeneratorManager.getAdminPostSearchPage(driver);
}


}
