package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.nopcommerce.user.UserHomePageUI;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class HomePageObject extends BasePageFactory {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using ="//a[text()='Register']")
	private WebElement registerLink;
	
	@FindBy(xpath ="//a[text()='Log in']")
	private WebElement loginLink;
	
	@FindBy(xpath ="//a[@class = 'ico-account']")
	private WebElement myAccountLink;
	
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);

	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
		
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, myAccountLink);
		return isElementDisplayed(driver, myAccountLink);
		
	}

}
