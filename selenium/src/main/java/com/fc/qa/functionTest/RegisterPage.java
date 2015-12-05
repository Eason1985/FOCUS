package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 新用户注册页面
 * @author Administrator
 *
 */

public class RegisterPage extends BasePageObject{

	public RegisterPage(LoginPage loginPage) {
		// TODO Auto-generated constructor stub
		super(loginPage);
	}
	
	/**
	 * 输入手机号
	 * @param iphone
	 */
	public void inputIphone(String iphone){
		String id="phoneNum";
		driver.findElement(By.id(id)).sendKeys(iphone);
	}
	
	/**
	 * 输入密码
	 * @param password
	 */
	public void inputPassword(String password){
		String id="loginPwd";
		driver.findElement(By.id(id)).sendKeys(password);
	}
	
	/**
	 * 获取验证码
	 * @throws InterruptedException
	 */
	public void getCode() throws InterruptedException{
		String linkText="获取";
		driver.findElement(By.linkText(linkText)).click();
		Thread.sleep(3000);
	}
	
	/**
	 * 输入验证码
	 * @param phoneMsg
	 */
	public void inputCode(String phoneMsg){
		String id="phoneMsg";
		driver.findElement(By.id(id)).sendKeys(phoneMsg);
	}
	
	/**
	 * 输入邮箱
	 * @param mail
	 */
	public void inputMail(String mail){
		String id="email";
		driver.findElement(By.id(id)).sendKeys(mail);
		waitForLoad(By.id("registerBtn"));
	}
	
	/**
	 * 注册
	 * @return
	 */
	public MyFinancePage confirmRegister(){
		String id="registerBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("realName"));
		return new MyFinancePage(this);
	}
}
