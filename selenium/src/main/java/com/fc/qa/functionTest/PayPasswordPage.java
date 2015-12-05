package com.fc.qa.functionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * 支付密码
 * @author Administrator
 *
 */
public class PayPasswordPage  extends BasePageObject{

	public PayPasswordPage (PaymentPage paymentPage) {
		// TODO Auto-generated constructor stub
		super(paymentPage);
	}
    
	public PayPasswordPage(YqbRedemPage yqbRedemPage) {
		// TODO Auto-generated constructor stub
		super(yqbRedemPage);
	}

	/**
	 * 输入支付密码
	 * @return 
	 * @throws InterruptedException 
	 */
	public PayFinishedPage inutPassword(String password) throws InterruptedException{
		password="111111";
		//输入支付密码
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id("payPassword"))).sendKeys(password).perform();
		//确定
		driver.findElement(By.id("confirmPwdBtn")).click();
//		Thread.sleep(20000);
		waitForLoad(By.linkText("完成"));
		return new PayFinishedPage(this);
	}
	
}
