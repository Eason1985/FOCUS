package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 易钱包赎回页
 * @author Administrator
 *
 */
public class YqbRedemPage extends BasePageObject {

	public YqbRedemPage(YqbPage yqbPage) {
		super(yqbPage);
	}
	public YqbRedemPage(BankListPage bankListPage) {
		// TODO Auto-generated constructor stub
		super(bankListPage);
	}
	/**
	 * 输入赎回金额
	 * @param amount
	 * @throws InterruptedException 
	 */
    
	public void inputAmount(String amount) throws InterruptedException{
		Thread.sleep(2000);
		String id="amount";
		driver.findElement(By.id(id)).sendKeys(amount);
	}
	
	/**
	 * 确认转出
	 * @return
	 */
	public PayPasswordPage confirm(){
		String id="confirmPayBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("payPassword"));
		return new PayPasswordPage(this);
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
	
	
	
}
