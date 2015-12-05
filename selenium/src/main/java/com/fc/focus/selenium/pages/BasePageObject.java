package com.fc.focus.selenium.pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fc.focus.selenium.utils.DriverBackedSelenium;

/**
 * 测试PageObject基类.
 * 
 * @author alan
 */
public class BasePageObject {
	protected static Log log = LogFactory.getLog(BasePageObject.class);

	protected DriverBackedSelenium selenium;
	protected WebDriver driver;
	
	protected WebDriverWait wait;
	protected BasePageObject sourcePage;
	protected Long oid;
	/**
	* DataTable 记录
	*/
	protected int index;
	protected String baseUrl;

	public BasePageObject(BasePageObject object) {
		this.selenium = object.getSelenium();
		this.driver = object.getDriver();
		this.sourcePage = object;
		this.oid = object.oid;
		this.baseUrl=object.baseUrl;
		
		//selenium.putWindowHandle(object.getClass().getSimpleName(), driver.getWindowHandle());
	}
	
	public boolean waitForLoad(final By elementBy) {  
        WebDriverWait wait = (new WebDriverWait(driver, 50));  
        return wait.until(new ExpectedCondition<Boolean>() {  
            public Boolean apply(WebDriver d) {  
                boolean loadcomplete = d.findElement(elementBy).isDisplayed();  
//                System.out.print(loadcomplete);
                return loadcomplete;  
            }  
        });  
    }  
	

	public BasePageObject(DriverBackedSelenium selenium) {
		this.selenium = selenium;
		this.driver = selenium.getDriver();
	}
	
	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}
    
	public BasePageObject(WebDriverWait wait){
		this.wait = wait;
	}
	public String getSearchPageUrl() {
		return baseUrl;
	}

	public String getSinglePageUrl() {
		return baseUrl + "/" + oid;
	}

	public String getCreateUrl() {
		return baseUrl + CREATE_ACTION;
	}

	public String getEditingUrl() {
		return getSinglePageUrl() + GET_UPDATE_ACTION;
	}

	public String getDeteleUrl() {
		return getSinglePageUrl() + DELETE_ACTION;
	}

	public BasePageObject search() {
		get(getSearchPageUrl());
		return this;
	}

	public void open() {
		get(getSinglePageUrl());
	}

	public void openCreateUrl() {
		get(getCreateUrl());
	}

	public void openEditingUrl() {
		get(getEditingUrl());
	}

	public void openDeleteUrl() {
		get(getDeteleUrl());
	}

	public void btnCreate() {
		selenium.waitForClick(BTN_NEW);
	}

	public void btnEditing() {
		selenium.waitForClick(BTN_EDIT);
		selenium.waitForPageToLoad("50000");
	}

	public void btnSave() {
		selenium.waitForClick(BTN_SAVE);
		selenium.waitForPageToLoad("50000");
	}

	public void btnRefresh() {
		selenium.waitForClick(BTN_REFRESH);
	}

	public void btnDelete() {
		selenium.byId(BTN_DELETE).click();
		selenium.alert().accept();
	}

	public void get(String pageUrl) {
		selenium.open(pageUrl);
		selenium.waitForPageToLoad("50000");
		log.info(selenium);
	}

	public void btnSearch() {
		selenium.waitForClick(BTN_SEARCH);
	}

	public DriverBackedSelenium getSelenium() {
		return selenium;
	}

	public void setSelenium(DriverBackedSelenium selenium) {
		this.selenium = selenium;
	}

	public BasePageObject getSourcePage() {
		return sourcePage;
	}

	public void setSourcePage(BasePageObject sourcePage) {
		this.sourcePage = sourcePage;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Toolbar button id
	 */
	protected final static String BTN_SAVE = "tbbt_Save";
	protected final static String BTN_NEW = "tbbt_New";
	protected final static String BTN_DELETE = "tbbt_Delete";
	protected final static String BTN_EDIT = "tbbt_Edit";
	protected final static String BTN_REFRESH = "tbbt_Refresh";
	protected final static String BTN_CANCEL = "tbbt_Cancel";
	protected final static String BTN_SEARCH = "tbbt_Cancel";

	/**
	 * action
	 */
	public final static String CREATE_ACTION = "?_action=create";
	public final static String GET_UPDATE_ACTION = "?_editing=true";
	public final static String POST_UPDATE_ACTION = "?_action=update";
	public final static String DELETE_ACTION = "?_action=delete";

}
