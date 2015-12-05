package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 如意宝产品列表页
 * @author Administrator
 *
 */
public class RybListPage extends BasePageObject{

	public RybListPage(ProductPage productPage) {
		// TODO Auto-generated constructor stub
		super(productPage);
	}
	/**
	 * 选择某一如意宝产品
	 * @return 
	 * @throws InterruptedException 
	 */
	public ProductDetailPage rybProduct() throws InterruptedException{
		String xpath="//*[@id='section1']/dl[1]/dd[2]/a";  
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(2000);
		return new ProductDetailPage(this);
	}

}
