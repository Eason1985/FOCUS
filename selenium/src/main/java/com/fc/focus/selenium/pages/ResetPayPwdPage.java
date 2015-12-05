package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * 重置支付密码
 * @author Administrator
 *
 */

public class ResetPayPwdPage extends BasePageObject{

	public ResetPayPwdPage(SettingPage settingPage) {
		// TODO Auto-generated constructor stub
		super(settingPage);
	}
	
	/**
	 * 输入原支付密码
	 * @param payPwd
	 */
	public void inputOriginPayPwd(String payPwd){
		String id="payPassword";
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id(id))).sendKeys(payPwd).perform();
	}
	
	/**
	 * 下一步，设置新密码
	 * @throws InterruptedException
	 */
	public void nextConfirm() throws InterruptedException{
		String id="confirmPwdBtn";
		driver.findElement(By.id(id)).click();
        Thread.sleep(2000);
	}
	
	/**
	 * 输入新密码
	 * @param newPayPwd
	 */
	public void inputNewpayPwd(String newPayPwd){
		String id="setPayPassword";
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id(id))).sendKeys(newPayPwd).perform();
	}
	
	/**
	 * 下一步
	 */
	public void nextStepBtn(){
		String id="nextStepBtn";
		driver.findElement(By.id(id)).click();
	}
	
	/**
	 * 第二次输入新密码
	 * @param newPayPwd
	 */
	public void inputSecondPwd(String newPayPwd){
		String id="confirmPayPassword";
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id(id))).sendKeys(newPayPwd).perform();
	}
	
	public MyFinancePage confirmPayPasswordBtn(){
		String id="confirmPayPasswordBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("toMyProfile"));
		return new MyFinancePage(this);
	}

}
