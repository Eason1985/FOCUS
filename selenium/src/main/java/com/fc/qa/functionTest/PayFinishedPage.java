package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 支付完成页
 * @author Administrator
 *
 */
public class PayFinishedPage extends BasePageObject {

	public PayFinishedPage(PayPasswordPage payPasswordPage) {
		// TODO Auto-generated constructor stub
		super(payPasswordPage);
	}
	/**
	 * 完成
	 * @return 
	 * @throws InterruptedException 
	 */
    
	public ProductPage finished() throws InterruptedException{
		driver.findElement(By.linkText("完成")).click();
		Thread.sleep(2000);
		return new ProductPage(this);
	}
	
	/**
	 * 跳转至财富页
	 * @throws InterruptedException 
	 */
	
	public MyFinancePage myFinance() throws InterruptedException{
		System.out.print(baseUrl);
		Thread.sleep(2000);
		driver.get(baseUrl+"/Dashboard/Dashboard.html");
		
		waitForLoad(By.id("realName"));
		return new MyFinancePage(this); 
	}

}
