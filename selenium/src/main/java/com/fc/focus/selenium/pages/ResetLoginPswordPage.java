package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 输入新密码页面
 * @author Administrator
 *
 */

public class ResetLoginPswordPage extends BasePageObject{

	public ResetLoginPswordPage(ForgetPasswordPage forgetPasswordPage) {
		// TODO Auto-generated constructor stub
		super(forgetPasswordPage);
	}
	/**
	 * 输入新密码
	 * @param password
	 */
	public void inputFirsPsword(String password){
		String id="firstpwd";
		driver.findElement(By.id(id)).sendKeys(password);
	}
	
	/**
	 * 再次输入密码
	 * @param password
	 */
	public void inputSecPsword(String password){
		String id="secondpwd";
		driver.findElement(By.id(id)).sendKeys(password);
	}
   
	public void confirm(){
		String id="confirmResePwdBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("iKnow"));
		driver.findElement(By.id("iKnow")).click();
	}
	
}
