package baseTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import extentlisteners.ExtentListeners;
import utility.ExcelReader;

public class BaseTest {
	/*
	 * 
	 * WebDriver TestNG Excel Screenshots Implicit /Explicit Wait keyword log4j
	 * Properties Extent Reports
	 * 
	 * Framework:
	 * 
	 * 1. End to End Launch browser Testcase1 Close browser launch browser Testcase2
	 * close Browser 2. Sequencial
	 * 
	 * Launch browser Testcase1 Testcase2 .. Close browser
	 */

	public static WebDriver driver;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excelData/fbData.xlsx");
	public static WebDriverWait wait;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static Logger log = Logger.getLogger(BaseTest.class);

	@BeforeSuite
	public void setUp() {
		try {
			fis = new FileInputStream("./src/test/resources/properties/log4j.properties");
		} catch (FileNotFoundException e) {
			log.info("log4j configuration file is not loaded");
		}
		PropertyConfigurator.configure(fis);
		log.info("Test case execution started");

		try {
			fis = new FileInputStream("./src/test/resources/properties/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.info("config properties file has been loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream("./src/test/resources/properties/or.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			or.load(fis);
			log.info("or properties file has been loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			log.info("Chrome browser has been launched");
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			log.info("Firefox browser has been launched");
		}

		else if (config.getProperty("browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			log.info("Edge browser has been launched");
		}
		driver.manage().window().maximize();
		log.info("Browser is maximized");
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicitWait"))));
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitWait"))));
		driver.get(config.getProperty("testsiteurl"));
	}

	/*
	 * @AfterSuite public void tearDown() { driver.quit(); }
	 */
	public static boolean isElementPresent(String keyword) {
		// type("passwordTxtbox_NAME","testing123");
		WebElement ele = null;
		try {
			if (keyword.endsWith("_ID")) {
				ele = driver.findElement(By.id(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_NAME")) {
				ele = driver.findElement(By.name(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_XPATH")) {
				ele = driver.findElement(By.xpath(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CSS")) {
				ele = driver.findElement(By.cssSelector(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CLASS")) {
				ele = driver.findElement(By.className(or.getProperty(keyword)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Element is not present");
			return false;
		}

	
		log.info("Element is present with the keyword as: " + keyword);
		ExtentListeners.test.info("Element is present with the keyword as:" + keyword);
		return true;
	}
	
	public static void type(String keyword, String value) {
		// type("passwordTxtbox_NAME","testing123");
		WebElement ele = null;
		try {
			if (keyword.endsWith("_ID")) {
				ele = driver.findElement(By.id(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_NAME")) {
				ele = driver.findElement(By.name(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_XPATH")) {
				ele = driver.findElement(By.xpath(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CSS")) {
				ele = driver.findElement(By.cssSelector(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CLASS")) {
				ele = driver.findElement(By.className(or.getProperty(keyword)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		ele.sendKeys(value);
		log.info("Entered value into fields with keyword as: " + keyword + " with value as: " + value);
		ExtentListeners.test.info("Entered value into fields with keyword as: " + keyword + " with value as: " + value);
	}

	public static void click(String keyword) {
		WebElement ele = null;
		// click("loginBtn_XPATH");
		try {
			if (keyword.endsWith("_ID")) {
				ele = driver.findElement(By.id(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_NAME")) {
				ele = driver.findElement(By.name(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_XPATH")) {
				ele = driver.findElement(By.xpath(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CSS")) {
				ele = driver.findElement(By.cssSelector(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CLASS")) {
				ele = driver.findElement(By.className(or.getProperty(keyword)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		ele.click();
		log.info("Clicked on button with keyword as : " + keyword);
		ExtentListeners.test.info("Clicked on button with keyword as : " + keyword);
	}
	
	public static void select(String keyword,String value) {
		WebElement ele = null;
		Select s;
		// click("loginBtn_XPATH");
		try {
			if (keyword.endsWith("_ID")) {
				ele = driver.findElement(By.id(or.getProperty(keyword)));
				
			}

			else if (keyword.endsWith("_NAME")) {
				ele = driver.findElement(By.name(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_XPATH")) {
				ele = driver.findElement(By.xpath(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CSS")) {
				ele = driver.findElement(By.cssSelector(or.getProperty(keyword)));
			}

			else if (keyword.endsWith("_CLASS")) {
				ele = driver.findElement(By.className(or.getProperty(keyword)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		s = new Select(ele);
		s.selectByVisibleText(value);
		log.info("Select the value from drop down with keyword as : " + keyword + " and value as: " + value);
		ExtentListeners.test.info("Select the value from drop down with keyword as : " + keyword + " and value as: " + value);
	}

}
