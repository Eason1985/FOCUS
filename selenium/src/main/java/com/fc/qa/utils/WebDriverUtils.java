package com.fc.qa.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.CompoundMutator;
import org.openqa.selenium.internal.seleniumemulation.ElementFinder;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	protected static Log log = LogFactory.getLog(WebDriverUtils.class);

	static JavascriptLibrary javascriptLibrary = new JavascriptLibrary();
	static ElementFinder elementFinder = new ElementFinder(javascriptLibrary);

	public static WebElement by(WebDriver driver, long timeOutInSeconds, By by) {
		return (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	

	public static WebElement by(WebDriver driver, int timeOutInSeconds, ElementFinder elementFinder, String locator) {
		return (new WebDriverWait(driver, timeOutInSeconds)).until(com.fc.qa.utils.ExpectedConditions.findElement(elementFinder, locator));
	}

	public static WebElement byId(WebDriver driver, long timeOutInSeconds, String id) {
		return by(driver, timeOutInSeconds, By.id(id));
	}

	public static Object runScript(CompoundMutator mutator, WebDriver driver, String locator) {
		return new WebDriverWait(driver, 10).until(com.fc.qa.utils.ExpectedConditions.runScript(mutator, locator));
	}

	public static WebElement type(WebDriver driver, long timeOutInSeconds, String id, String valueToUse) {
		WebElement element = byId(driver, timeOutInSeconds, id);

		String tagName = element.getTagName();
		String elementType = element.getAttribute("type");
		if ("input".equals(tagName.toLowerCase()) && elementType != null && "file".equals(elementType.toLowerCase())) {
			log.warn("You should be using attachFile to set the value of a file input element");
			element.sendKeys(valueToUse);
			return element;
		}

		if (!"input".equals(tagName.toLowerCase())) {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
			}
			element.sendKeys(valueToUse);
			return element;
		}

		if (driver instanceof JavascriptExecutor) {
			javascriptLibrary.executeScript(driver, "return (" + javascriptLibrary.getSeleniumScript("type.js") + ").apply(null, arguments);", element, valueToUse);
		} else {
			element.clear();
			element.sendKeys(valueToUse);
		}
		return element;
	}

}
