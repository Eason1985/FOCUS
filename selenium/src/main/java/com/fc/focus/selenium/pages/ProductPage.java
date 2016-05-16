package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 产品类别页
 * @author Administrator
 *
 */

public class ProductPage extends BasePageObject{

	public ProductPage(PayFinishedPage payFinishedPage) {
		// TODO Auto-generated constructor stub
		super(payFinishedPage);
	}
	
	public ProductPage(MyFinancePage myFinancePage) {
		// TODO Auto-generated constructor stub
		super(myFinancePage);
	}

	public ProductPage(RegisterSuccessPage registerSuccessPage) {
		// TODO Auto-generated constructor stub
		super(registerSuccessPage);
	}

	/**
	 * 如意宝列表
	 * @return 
	 * @throws InterruptedException 
	 */
	public RybListPage rybCategory() throws InterruptedException{
		
		String xpath="/html/body/div[1]/div[3]/ul/li[2]/a";   
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(2000);
		return new RybListPage(this);
	}

}
