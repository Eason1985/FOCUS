package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 易钱包转入转出详情页
 * @author Administrator
 *
 */

public class YqbOrderDetailPage extends BasePageObject{

	public YqbOrderDetailPage(YqbListPage yqbListPage) {
		// TODO Auto-generated constructor stub
		super(yqbListPage);
	}
	
	/**
	 * 获取易钱包转入转出金额
	 * @return
	 * @throws InterruptedException 
	 */
	public float amount() throws InterruptedException{
		Thread.sleep(2000);
		String id="yqbtotal";
		String amount=driver.findElement(By.id(id)).getText();
		String str = new String(amount);
    	str=str.replace(",", "");
    	str=str.replace("+", "");
    	str=str.replace("-", "");
    	return Float.parseFloat(str);
	}
	
	public String detailSteps(){
		String id="steps";
		String steps=driver.findElement(By.id(id)).getText();
		return steps;
	}

}
