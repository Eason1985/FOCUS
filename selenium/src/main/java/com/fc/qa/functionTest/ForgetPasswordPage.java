package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 忘记密码页
 * @author Administrator
 *
 */
public class ForgetPasswordPage extends BasePageObject{

	public ForgetPasswordPage(LoginPage loginPage) {
		super(loginPage);
	}
	
	/**
	 * 输入手机号
	 */
	public void inputIphone(String iphoneNum){
//		String iphoneNum="";
		String id="phonenum";
		driver.findElement(By.id(id)).sendKeys(iphoneNum);
	}
	
	/**
	 * 获取验证码
	 * @throws InterruptedException
	 */
	public  void getSmsCaptcha() throws InterruptedException {
		String id="getCode";
		driver.findElement(By.id(id)).click();
		Thread.sleep(3000);
	}
   
	/**
	 * 输入验证码
	 * @param smsCaptcha
	 */
	public void inputSmsCaptcha(String smsCaptcha){
		String id="smsCaptcha";
		driver.findElement(By.id(id)).sendKeys(smsCaptcha);
		waitForLoad(By.id("authCellBtn"));
	}
	
	public ResetLoginPswordPage resetPassword(){
		String id="authCellBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("confirmResePwdBtn"));
		return new ResetLoginPswordPage(this);
	}
}
