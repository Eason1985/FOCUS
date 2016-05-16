package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 易钱包转入转出列表页
 * @author Administrator
 *
 */

public class YqbListPage extends BasePageObject{

	public YqbListPage(YqbPage yqbPage) {
		// TODO Auto-generated constructor stub
		super(yqbPage);
	}
	
	//转入列表
	public void rollInList() {
		String id="shiftIn";
		driver.findElement(By.id(id)).click();
		
	}
	
	//转出列表
	public  void rollOut() throws InterruptedException {
		String id="rollOut";
		driver.findElement(By.id(id)).click();
//		waitForLoad(By.id(""));
		Thread.sleep(2000);
		
	}
	
	//转出金额
	public float rollOutAmount(){
		String xpath="//*[@id='rollOutContext']/li[1]/a/div[2]";   
		String amount=driver.findElement(By.xpath(xpath)).getText();
		String str = new String(amount);
    	str=str.replace(",", "");
    	str=str.replace("+", "");
    	str=str.replace("-", "");
    	return Float.parseFloat(str);
		
	}
	//转出交易
	public YqbOrderDetailPage rollOutDetail(){
		String xpath="//*[@id='rollOutContext']/li[1]";
		driver.findElement(By.xpath(xpath)).click();
		waitForLoad(By.id("steps"));
		return new YqbOrderDetailPage(this);
	}
	
	//转入金额
	public float rollInAmount(){
		String xpath="//*[@id='shiftInContext']/li[1]/a/div[2]";   
		String amount=driver.findElement(By.xpath(xpath)).getText();
		String str = new String(amount);
    	str=str.replace(",", "");
    	str=str.replace("+", "");
    	str=str.replace("-", "");
    	return Float.parseFloat(str);
		
	}
	
	//转入交易
		public YqbOrderDetailPage rollInDetail(){
			String xpath="//*[@id='shiftInContext']/li[1]";
			driver.findElement(By.xpath(xpath)).click();
			waitForLoad(By.id("steps"));
			return new YqbOrderDetailPage(this);
		}
	

}
