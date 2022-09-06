package pageObject.wordpress;

import org.openqa.selenium.WebDriver;



public class PageGeneratorManager {
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	public static AdminDashboadPageObject getAdminDashBoardPage(WebDriver driver) {
		return new AdminDashboadPageObject(driver);
	}
	public static AdminPostSearchPageObject getAdminPostSearchPage(WebDriver driver) {
		return new AdminPostSearchPageObject(driver);
	}
	public static AdminPostAddNewPageObject getAdminPostAddNewPage(WebDriver driver) {
		return new AdminPostAddNewPageObject(driver);
	}
	public static UserPostDetailPO getUserPostDetailPage(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
	public static UserSearchPostPO getUserSearchPostPage(WebDriver driver) {
		return new UserSearchPostPO(driver);
	}
}