package com.fc.focus.selenium.utils;

import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.CompoundMutator;
import org.openqa.selenium.internal.seleniumemulation.ElementFinder;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditions {
	public final static Logger log = Logger.getLogger(ExpectedConditions.class.getName());

	private ExpectedConditions() {
		// Utility class
	}

	public static ExpectedCondition<Object> runScript(final CompoundMutator mutator, final String locator) {
		return new ExpectedCondition<Object>() {
			public Object apply(WebDriver driver) {
				StringBuilder builder = new StringBuilder();
				mutator.mutate(locator, builder);
				return ((JavascriptExecutor) driver).executeScript(builder.toString());
			}
		};
	}

	public static ExpectedCondition<WebElement> findElement(final ElementFinder elementFinder, final String locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				return elementFinder.findElement(driver, locator);
			}
		};
	}
}
