package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.user.UserAddressPageObject;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopcommerce.user.UserRewardPointPage;
import pageUIs.nopcommerce.user.BasePageUI;

public class BasePage {
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {

				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")|| locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		}
		else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")||locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		}
		else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		}
		else if (locatorType.startsWith("xpath=")|| locatorType.startsWith("Xpath=")|| locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		}
		else {
			throw new RuntimeException("Locator type is not support! ");
		}
		return by;
	}
	private String getDynamicXpath(String locatorType, String...dynamicValues) {
		if (locatorType.startsWith("xpath=")|| locatorType.startsWith("Xpath=")|| locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType,(Object[]) dynamicValues);
		}
		return locatorType;
		
	}
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	private List<WebElement> getListWebElement(WebDriver driver, String locatorType ) {
		return driver.findElements(getByLocator(locatorType));
	}
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String...dynamicValues ) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType,dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)));
		select.selectByValue(textItem);
	}
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	public void selectItemCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(3);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			if (actualTextItem.equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
			
		}
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String getElementAtribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).getText();
	}
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	public int getElementSize(WebDriver driver,String locatorType ) {
		return getListWebElement(driver, locatorType).size();
	}
	public int getElementSize(WebDriver driver,String locatorType, String...dynamicValues ) {
		return getListWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).size();
	}
	public void checkToDefaultCheckboxRadio(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToDefaultCheckbox(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	public boolean isElementDisplayed(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	public boolean isElementDisplayed(WebDriver driver,String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).isDisplayed();
	}
	public void switchToFrameIframe(WebDriver driver,String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}



	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}


	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementVisible(WebDriver driver, String locatorType, String dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType,String dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	public void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementInVisible(WebDriver driver, String locatorType, String dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	public void waitForAllElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	public void waitForAllElementInVisible(WebDriver driver, String locatorType, String dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType,dynamicValues))));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType, String dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType,dynamicValues))));
	}
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	public UserRewardPointPage openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	public UserCustomerInforPageObject openCustomerInforPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		return PageGeneratorManager.getUserCustomerInfo(driver);
	}
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfo(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);

		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}
	public void openPagesAtMyAccountPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
	}
	public static final String ADDRESS_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='My product reviews']";
	public static final String REWARD_POINT_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Reward points']";
	public static final String CUSTOMER_INFOR_LINK = "xpath=//div[@class='block block-account-navigation']//a[text()='Customer info']";
	
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
    public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
    	waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	private long longTimeout = 30;
	private long shortTimeout = 5;
	
}
