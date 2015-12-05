package com.fc.qa.functionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 我的如意宝列表
 * @author Administrator
 *
 */

public class MyRybListPage extends BasePageObject{

	public MyRybListPage(MyFinancePage myFinancePage) {
		super(myFinancePage);
	}
	
	/**
	 * 待确认
	 * @throws InterruptedException 
	 */
	public void unConfirmed(int height) throws InterruptedException{
		
		driver.findElement(By.id("unconfirmed")).click();		
		 if (driver instanceof JavascriptExecutor) {
             ((JavascriptExecutor)driver).executeScript("scrollTo(0,document.body.scrollHeight)");

               }
		
	}
	

}
