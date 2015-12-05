package com.fc.qa.functionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * 设置支付密码页
 * @author Administrator
 *
 */

public class SetPayPaswordPage extends BasePageObject{

	public SetPayPaswordPage(MyFinancePage myFinancePage) {
		// TODO Auto-generated constructor stub
		super(myFinancePage);
	}
	
	/**
	 * 原支付密码
	 * @param paypwd
	 */
	public void beforePayPwd(String paypwd){
		String id="payPassword";
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id(id))).sendKeys(paypwd).perform();
	}
	/**
	 * 输入原密码后下一步
	 */
	public void next(){
		String id="confirmPwdBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("setPayPassword"));
	}
	
	/**
	 * 第一次密码
	 * @param password
	 */
	public void firstPassword(String password){
		password="111111";
		//输入支付密码
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id("setPayPassword"))).sendKeys(password).perform();
	}
	
	/**
	 * 点击下一步
	 */
	public void nextStep(){
		String id="nextStepBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("confirmPayPassword"));
	}
	
	/**
	 * 第二次密码
	 * @param password
	 */
	public void secondPassword(String password){
		password="111111";
		//输入支付密码
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id("confirmPayPassword"))).sendKeys(password).perform();
	}
	
	/**
	 * 确定
	 * @return
	 */
	public FirstAddBankPage confirm(){
		String id="confirmPayPasswordBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("selDocuments"));
		return new FirstAddBankPage(this);
		
	}
	
	/**
	 * 重设
	 * @throws InterruptedException
	 */
	public void resetPassword() throws InterruptedException{
		String id="resetPayPassword";
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
	}

}
