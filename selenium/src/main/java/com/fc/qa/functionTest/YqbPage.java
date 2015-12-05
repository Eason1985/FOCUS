package com.fc.qa.functionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fc.qa.utils.DriverBackedSelenium;
/**
 * 易钱包页面
 * @author Administrator
 *
 */

public class YqbPage extends BasePageObject{

	public YqbPage(MyFinancePage myFinancePage) {
		super(myFinancePage);
	}
	/**
	 * 易钱包转入按钮
	 */
	private String rollInButton;
    public YqbBuyPage rollIn(){
    	rollInButton="rollInBtn";
    	driver.findElement(By.id(rollInButton)).click();
    	waitForLoad(By.id("proName"));
    	return new YqbBuyPage(this);
    }
    /**
     * 易钱包转出按钮
     */
    private String rollOutButton;
    public YqbRedemPage rollOut(){
    	rollOutButton="rollOutBtn";
    	driver.findElement(By.id(rollOutButton)).click();
    	return new YqbRedemPage(this);
    }
    
    /**
     * 获取易钱包总额
     * @return
     */
    public float totalAmount(){
    	String id="totalAmount";
    	String amount=driver.findElement(By.id(id)).getText();
    	String str = new String(amount);
    	str=str.replace(",", "");
    	return Float.parseFloat(str);
    }
    
    public YqbListPage yqbList(){
    	String id="totalAmount";
    	driver.findElement(By.id(id)).click();
    	waitForLoad(By.id("shiftIn"));
    	return new YqbListPage(this);
    }
}
