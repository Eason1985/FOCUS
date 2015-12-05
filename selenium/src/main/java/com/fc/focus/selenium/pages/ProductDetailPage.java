package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;
/**
 * 如意宝详情页
 * @author Administrator
 *
 */
public class ProductDetailPage extends BasePageObject{

	public ProductDetailPage(RybListPage rybListPage) {
		super(rybListPage);
	}
	
	/**
	 * 投资
	 * @return 
	 * @throws InterruptedException 
	 */
	
	public RybOrderPage rybInvest() throws InterruptedException{
		String id="investBtn";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
		return new RybOrderPage(this);
	}

}
