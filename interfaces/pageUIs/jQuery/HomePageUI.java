package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGE = "CSS=ul.qgrd-pagination-ul>li.qgrd-pagination-page";
	public static final String PAGINATION_PAGE_INDEX = "XPATH=//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]/a";
	public static final String ALL_ROW_EACH_PAGE = "css=tbody>tr";
	public static final String ALL_ROW_COUNTRY_EACH_PAGE = "XPATH=//tbody/tr/td[@data-key='country']";
}
