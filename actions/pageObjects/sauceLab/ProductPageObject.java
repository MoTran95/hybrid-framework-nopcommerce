package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
	}

	public boolean isProductNameSortByAscending() {
		List<String> productUIList = new ArrayList<String>();
		
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		List<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByDecsending() {
		List<String> productUIList = new ArrayList<String>();
		
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		List<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		
		
		return productSortList.equals(productUIList);
	}
	public boolean isProductNameSortByDecsending2() {
		List<String> productUIList = new ArrayList<String>();
		
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}
		List<String> productSortList = new ArrayList<>(productUIList);
	
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		
		
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		List<Float> productUIList = new ArrayList<Float>();
		
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		List<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDecsending() {
		List<Float> productUIList = new ArrayList<Float>();
		
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		List<Float> productSortList = new ArrayList<>(productUIList);
		
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		
		return productSortList.equals(productUIList);
	}
}
