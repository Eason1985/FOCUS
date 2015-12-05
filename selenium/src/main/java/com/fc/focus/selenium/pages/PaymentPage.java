package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 支付页面
 * @author Administrator
 *
 */
public class PaymentPage extends BasePageObject {

	public PaymentPage(YqbBuyPage yqbBuyPage) {
		// TODO Auto-generated constructor stub
		super(yqbBuyPage);
	}
	
	public PaymentPage(RybOrderPage rybOrderPage) {
		// TODO Auto-generated constructor stub
		super(rybOrderPage);
	}
	/**
	 * 银行卡支付
	 * @throws InterruptedException 
	 */
	public void payBank() throws InterruptedException{
		String id="canUseBank";
		driver.findElement(By.id(id)).click();
//		Thread.sleep(2000);
	}
	/**
	 * 更换银行卡
	 * @return 
	 * @throws InterruptedException 
	 */
	public BankListPage changeBank() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='selPayment']/div[3]/span")).click();  
//		Thread.sleep(2000);
		waitForLoad(By.id("paymentList"));
		return new BankListPage(this);
	}
	
	public void payYqb() throws InterruptedException{
		String id="moneyNull";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
	}

	/**
	 * 确认支付
	 * @return 
	 * @throws InterruptedException 
	 */
	public PayPasswordPage confirmPay() throws InterruptedException{
		driver.findElement(By.id("confirmPayBtn")).click();
//		Thread.sleep(2000);
		waitForLoad(By.id("payPassword"));
		return new PayPasswordPage(this);
	}

}
