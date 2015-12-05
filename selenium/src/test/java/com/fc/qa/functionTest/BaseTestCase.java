package com.fc.qa.functionTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fc.qa.utils.DriverBackedSelenium;

/**
 * 测试基类.
 * @author alan
 */
@Test
public class BaseTestCase {
	protected static Log log = LogFactory.getLog(BaseTestCase.class);

	protected static WebDriver driver;
	protected static WebDriverBackedSelenium selenium;
	protected static WebDriverWait wait;

	protected Long oid;
	protected static String baseUrl;

	@BeforeClass
	public static void startSelenium() throws Exception {
		createSelenium();
	}

	@AfterClass
	public static void stopSelenium() throws Exception {
		quitSelenium();
	}

	//读配置文件
	private static Properties getConfig() {
		//获取properties
		Properties p = new Properties();
		InputStream in = Object.class.getResourceAsStream("/com/fc/qa/functionTest/config.properties");
		try {
			p.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * 创建Selenium. 
	 * Chrome 浏览器
	 * @throws Exception 
	 */
	protected static void createSelenium() throws Exception {
		try {
			//PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:/spring/test.properties");
			// System.setProperty("webdriver.chrome.driver", propertiesLoader.getProperty("webdriver.chrome.driver"));
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments(propertiesLoader.getProperty("selenium.server.options"));

			Properties properties = getConfig();
			String dir = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", dir + "/src/test/resource/chromedriver.exe");

			driver = new ChromeDriver();

			baseUrl = properties.getProperty("ip");
			
		    wait = new WebDriverWait(driver, 2000);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 关闭Selenium.
	 * @throws InterruptedException 
	 */
	protected static void quitSelenium() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	/**
	 * URL
	 */
	protected final static String LOGIN = "/login";
}
