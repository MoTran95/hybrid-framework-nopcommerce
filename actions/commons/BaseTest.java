package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			System.out.println("Run firefox");
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserList == BrowserList.CHROME) {
			System.out.println("Run Chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserList == BrowserList.H_CHROME) {
			System.out.println("Run Chrome");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserList == BrowserList.EDGE) {
			System.out.println("Run edge");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_DEV_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			System.out.println("Run firefox");
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserList == BrowserList.CHROME) {
			System.out.println("Run Chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserList == BrowserList.H_CHROME) {
			System.out.println("Run Chrome");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browserList == BrowserList.EDGE) {
			System.out.println("Run edge");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

	protected String getEnvironmentUrl(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
		if (environment == EnvironmentList.DEV) {
			envUrl = "https://demo.nopcommerce.com";
		} else if (environment == EnvironmentList.STAGING) {
			envUrl = "https://admin-demo.nopcommerce.com";
		}
		return envUrl;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void deleteAllureReport() {
		try {

			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	String projectPath = System.getProperty("user.dir");

}
