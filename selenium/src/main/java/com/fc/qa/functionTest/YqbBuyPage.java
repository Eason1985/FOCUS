package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 易钱包购买页
 * @author Administrator
 *
 */

public class YqbBuyPage extends BasePageObject{

	public YqbBuyPage(YqbPage yqbPage) {
		super(yqbPage);
	}
	/**
	 * 易钱包购买金额
	 */
	private String amount;
	public PaymentPage createOrder(String amount) throws InterruptedException{
//		amount="1000";
//		Thread.sleep(2000);
		driver.findElement(By.id("moneyText")).sendKeys(amount);
		driver.findElement(By.id("toCreatOrder")).click();
//		Thread.sleep(2000);
		waitForLoad(By.id("canUseBank"));
		return new PaymentPage(this);
	}

}
