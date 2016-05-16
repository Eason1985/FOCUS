package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 我的交易列表
 * @author Administrator
 *
 */

public class MyTradeListPage extends BasePageObject {

	public MyTradeListPage(MyFinancePage myFinancePage) {
		// TODO Auto-generated constructor stub
		super(myFinancePage);
	}
	/**
	 * 全部
	 * @throws InterruptedException
	 */
	public void allTrade() throws InterruptedException{
		String id="all";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
	}
    
	/**
	 * 进行中
	 * @throws InterruptedException
	 */
	public void goingTrade() throws InterruptedException{
		String id="alldoing";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
	}
	
	/**
	 * 全部交易金额
	 * @return
	 */
	public float allAmount(){
		String xpath="//*[@id='mytradList']/div/div/div[1]/div[2]/h2"; 
		String amount=driver.findElement(By.xpath(xpath)).getText();
		String str = new String(amount);
    	str=str.replace(",", "");
    	str=str.replace("+", "");
    	str=str.replace("-", "");
    	return Float.parseFloat(str);
	}
	
	/**
	 * 进行中金额
	 * @return
	 */
	public float goingAmount(){
		String xpath="//*[@id='going']/div/div/div[1]/div[2]/h2";
		String amount=driver.findElement(By.xpath(xpath)).getText();
		String str = new String(amount);
    	str=str.replace(",", "");
    	str=str.replace("+", "");
    	str=str.replace("-", "");
    	return Float.parseFloat(str);
	}
	
	public TradeDetailPage orderDetail(){
		String className="inner-container.clearfix";
		driver.findElement(By.className(className)).click();
		waitForLoad(By.id("transAmount"));
		return new TradeDetailPage(this);
	}
}
