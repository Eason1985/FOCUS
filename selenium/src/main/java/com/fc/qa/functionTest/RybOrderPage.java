package com.fc.qa.functionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * 如意宝购买页
 * @author Administrator
 *
 */

public class RybOrderPage extends BasePageObject{

	public RybOrderPage(ProductDetailPage productDetailPage) {
		super(productDetailPage);
	}
	
	/**
	 * 输入购买份数
	 * @throws InterruptedException 
	 */
	public void addNum(String xpath) throws InterruptedException{
		xpath="/html/body/div[2]/div/div[2]/ul/li[3]/div[2]/div/button[2]"; 
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(2000);
		
	}
	
	/**
	 * 去支付
	 * @return 
	 * @throws InterruptedException 
	 */
	public PaymentPage gotoPay() throws InterruptedException{
		String id="toCreatOrder";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
		return new PaymentPage(this);
	}
	
	public void inputNum(String num) throws InterruptedException{
		String xpath="/html/body/div[1]/div/div[2]/ul/li[3]/div[2]/div/input"; 
		Actions actions = new Actions(driver);
		driver.findElement(By.cssSelector("input.unitprice")).clear();
		actions.click(driver.findElement(By.cssSelector("input.unitprice"))).sendKeys(num).perform();
		Thread.sleep(2000);
	}

}
