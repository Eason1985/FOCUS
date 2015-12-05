package com.fc.focus.selenium.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fc.focus.selenium.utils.exception.EmptyRecordException;
import com.fc.focus.selenium.utils.exception.EmptyRecordException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.seleniumemulation.ElementFinder;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Supplier;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 * 融合了Selenium 1.0 API与Selenium 2.0的By选择器的API.
 *
 * @author Alan
 */
public class DriverBackedSelenium extends DefaultSelenium implements WrapsDriver {

	protected static Log log = LogFactory.getLog(DriverBackedSelenium.class);

	public WebDriver driver;

	public ElementFinder elementFinder;

	protected Map<String, String> pageObjectWindowHandle = new HashMap<String, String>();

	public DriverBackedSelenium(Supplier<WebDriver> maker, String baseUrl) {
		super(new DriverCommandProcessor(baseUrl, maker));
		driver = getCommandProcessor().getDriver();
		elementFinder = getCommandProcessor().getElementFinder();
	}

	public DriverBackedSelenium(WebDriver baseDriver, String baseUrl) {
		super(new DriverCommandProcessor(baseUrl, baseDriver));
		driver = getCommandProcessor().getDriver();
		elementFinder = getCommandProcessor().getElementFinder();
	}

	public WebDriver getWrappedDriver() {
		return ((WrapsDriver) commandProcessor).getWrappedDriver();
	}

	public DriverCommandProcessor getCommandProcessor() {
		return (DriverCommandProcessor) commandProcessor;
	}

	public WebDriver getWebDriver() {
		return getCommandProcessor().getDriver();
	}

	public String getBaseUrl() {
		return getCommandProcessor().getBaseUrl();
	}

	/**
		// Get the text of the alert or prompt
		log.info(alert.getText());
		// And acknowledge the alert (equivalent to clicking "OK")
		alert.accept();
	 */
	public Alert alert() {
		Alert alert = driver.switchTo().alert();
		log.info(alert.getText());
		return alert;
	}

	public WebElement waitForDisplay(WebElement element, int timeout) {
		long timeoutTime = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < timeoutTime) {
			if (element.isDisplayed()) {
				return element;
			}
		}
		log.warn("waitForDisplay timeout");
		return element;
	}

	public WebElement waitForDisplay(String id) {
		WebElement element = waitForDisplay(id, 5000);
		return element;
	}

	/**
	 * locator = "tbbt_Save"
	 * locator = "xpath=//a[contains(@href,'#tab4')]"
	 * 
	 * 注意如果button click after is Alert，则不能使用该方法，直接by(By).click()
	 * @param locator
	 */
	public void waitForClick(String locator) {
		waitForClick(locator, 5000);
	}

	public void waitForClick(String locator, int timeout) {
		WebElement element = waitForDisplay(locator, timeout);
		element.click();
		waitForMaskHide(timeout); // click 完成以后等待timeouts
	}

	public void waitForMaskHide(int timeout) {
		long timeoutTime = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < timeoutTime) {
			String eval = getEval("BAF.patui.wait.cfg.getProperty('visible')");
			if ("false".equals(eval)) {
				return;
			}
			if (System.currentTimeMillis() % 10 == 0)
				log.warn(System.currentTimeMillis() + " ：click after wait mask hide...");
		}
		log.warn("mask hide timeout");
	}

	public void waitForDataTableLoad(int timeout) throws EmptyRecordException {
		waitForDataTableLoad(timeout, false);
	}

	/**
	 * wait for dataTable Load Record
	 * @param timeout
	 * @throws Exception 
	 */
	public void waitForDataTableLoad(int timeout, boolean isThrowException) throws EmptyRecordException {
		long timeoutTime = System.currentTimeMillis() + timeout;
		int length = 0;
		while (System.currentTimeMillis() < timeoutTime) {
			length = Integer.parseInt(getEval("bmoDataTable.getRecordSet().getLength()"));
			if (length > 0)
				return;
			if (System.currentTimeMillis() % 10 == 0)
				log.warn(System.currentTimeMillis() + " wait for data table load record...");
		}

		log.warn("wait for data table load record timeout.");

		if (isThrowException) {
			length = Integer.parseInt(getEval("bmoDataTable.getRecordSet().getLength()"));

			if (length < 1) {
				throw new EmptyRecordException("Can not find dataTable record!");
			}
		}
	}

	/**
	 * 当有可能button click有遮罩层，使用该方法
	 * @param id
	 * @param timeout
	 * @return
	 */
	public WebElement waitForDisplay(String locator, int timeout) {
		WebElement element = findElement(locator);
		long timeoutTime = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < timeoutTime) {
			String eval = getEval("BAF.patui.wait.cfg.getProperty('visible')");
			if ("false".equals(eval) && element.isDisplayed()) {
				return element;
			}
			if (System.currentTimeMillis() % 10 == 0)
				log.warn(System.currentTimeMillis() + "：Loading, please wait..." + (!element.isDisplayed() ? " and element.isDisplayed : " + locator : ""));
		}
		log.warn("waitForDisplay timeout");
		return element;
	}

	public WebElement by(By by) {
		return WebDriverUtils.by(driver, 10, by);
	}

	public WebElement by(String locator) {
		return WebDriverUtils.by(driver, 10, elementFinder, locator);
	}

	public WebElement by(String locator, int timeOutInSeconds) {
		return WebDriverUtils.by(driver, timeOutInSeconds, elementFinder, locator);
	}

	public WebElement byId(String id) {
		return WebDriverUtils.byId(driver, 10, id);
	}

	public WebElement byId(String id, long timeOutInSeconds) {
		return WebDriverUtils.byId(driver, timeOutInSeconds, id);
	}

	/**
	 * 轮询Wait(default:10s)调用脚本，如果只需执行一次脚本使用runScript
	 * @param locator
	 * @return
	 */
	public Object runScriptWait(String locator) {
		return WebDriverUtils.runScript(getCommandProcessor().getScriptMutator(), driver, locator);
	}

	/**
	 * selenium.type("primaryInsured", insured_hidden);
	 */
	public WebElement typeById(String id, String valueToUse) {
		return WebDriverUtils.type(driver, 10, id, valueToUse);
	}

	// Driver 函數  //
	/**
	 * 打开地址,如果url为相对地址, 自动添加baseUrl.
	 */
	public void openUrl(String url) {
		final String urlToOpen = url.indexOf("://") == -1 ? getBaseUrl() + (!url.startsWith("/") ? "/" : "") + url : url;
		driver.get(urlToOpen);
	}

	/**
	 * 获取当前页面.
	 */
	public String getLocation() {
		return driver.getCurrentUrl();
	}

	/**
	 * 回退历史页面。
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 刷新页面。
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * 获取页面标题.
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 退出Selenium.
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * 设置如果查找不到Element时的默认最大等待时间。
	 */
	public void setTimeout(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * 获取WebDriver实例, 调用未封装的函数.
	 */
	public WebDriver getDriver() {
		return driver;
	}

	//Elemenet 函數//

	public WebElement findElement(String locator) {
		return elementFinder.findElement(driver, locator);
	}

	/**
	 * 查找Element.
	 */
	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	/**
	 * 查找所有符合条件的Element.
	 */
	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	/**
	 * 判断页面内是否存在Element.
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * 判断Element是否可见.
	 */
	public boolean isVisible(By by) {
		return driver.findElement(by).isDisplayed();
	}

	/**
	 * 在Element中输入文本内容.
	 */
	public void type(By by, String text) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * 点击Element.
	 */
	public void click(By by) {
		driver.findElement(by).click();
	}

	/**
	 * 选中Element.
	 */
	public void check(By by) {
		WebElement element = driver.findElement(by);
		check(element);
	}

	/**
	 * 选中Element.
	 */
	public void check(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * 取消Element的选中.
	 */
	public void uncheck(By by) {
		WebElement element = driver.findElement(by);
		uncheck(element);
	}

	/**
	 * 取消Element的选中.
	 */
	public void uncheck(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	/**
	 * 判断Element有否被选中.
	 */
	public boolean isChecked(By by) {
		WebElement element = driver.findElement(by);
		return isChecked(element);
	}

	/**
	 * 判断Element有否被选中.
	 */
	public boolean isChecked(WebElement element) {
		return element.isSelected();
	}

	/**
	 * 返回Select Element,可搭配多种后续的Select操作.
	 * eg. s.getSelect(by).selectByValue(value);
	 */
	public Select getSelect(By by) {
		return new Select(driver.findElement(by));
	}

	/**
	 * 获取Element的文本.
	 */
	public String getText(By by) {
		return driver.findElement(by).getText();
	}

	/**
	 * 获取Input框的value.
	 */
	public String getValue(By by) {
		return getValue(driver.findElement(by));
	}

	/**
	 * 获取Input框的value.
	 */
	public String getValue(WebElement element) {
		return element.getAttribute("value");
	}

	// WaitFor 函數 //
	/**
	 * 等待Element的内容可见, timeout单位为秒.
	 */
	public void waitForVisible(By by, int timeout) {
		waitForCondition(ExpectedConditions.visibilityOfElementLocated(by), timeout);
	}

	/**
	 * 等待Element的内容为text, timeout单位为秒.
	 */
	public void waitForTextPresent(By by, String text, int timeout) {
		waitForCondition(ExpectedConditions.textToBePresentInElement(by, text), timeout);
	}

	/**
	 * 等待Element的value值为value, timeout单位为秒.
	 */
	public void waitForValuePresent(By by, String value, int timeout) {
		waitForCondition(ExpectedConditions.textToBePresentInElementValue(by, value), timeout);
	}

	/**
	 * 等待其他Conditions, timeout单位为秒.
	 * 
	 * @see #waitForTextPresent(By, String, int)
	 * @see ExpectedConditions
	 */
	public void waitForCondition(ExpectedCondition<?> conditon, int timeout) {
		(new WebDriverWait(driver, timeout)).until(conditon);
	}

	// Selenium1.0 函數 //

	/**
	 * 简单判断页面内是否存在文本内容.
	 */
	public boolean isTextPresent(String text) {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		return bodyText.contains(text);
	}

	/**
	 * 取得单元格的内容, 序列从0开始, Selnium1.0的常用函数.
	 */
	public String getTable(WebElement table, int rowIndex, int columnIndex) {
		return table.findElement(By.xpath("//tr[" + (rowIndex + 1) + "]//td[" + (columnIndex + 1) + "]")).getText();
	}

	/**
	 * 取得单元格的内容, 序列从0开始, Selnium1.0的常用函数.
	 */
	public String getTable(By by, int rowIndex, int columnIndex) {
		return getTable(driver.findElement(by), rowIndex, columnIndex);
	}

	/**
	 * Window
	 */
	public void closeOtherWindow() {
		String current = driver.getWindowHandle();

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(current))
				driver.switchTo().window(handle).close();
		}

		driver.switchTo().window(current);
	}

	public void switchToOtherWindow() {
		String current = driver.getWindowHandle();

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(current))
				driver.switchTo().window(handle);
		}
	}

	public String switchToNewWindow() {
		for (String handle : driver.getWindowHandles()) {
			if (!pageObjectWindowHandle.containsValue(handle)) {
				driver.switchTo().window(handle);
				return handle;
			}
		}

		return driver.getWindowHandle();
	}

	public void putWindowHandle(String simpleName, String windowHandle) {
		pageObjectWindowHandle.put(simpleName, windowHandle);
	}

	public String getWindowHandle(String simpleName) {
		return pageObjectWindowHandle.get(simpleName);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("WindowHandle:").append(driver.getWindowHandle()).append("\r\n");
		buffer.append("CurrentUrl:").append(driver.getCurrentUrl()).append("\r\n");
		buffer.append("Title:").append(driver.getTitle());
		return buffer.toString();
	}

}
