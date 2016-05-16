package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 设置页面
 * @author Administrator
 *
 */

public class SettingPage extends BasePageObject{

	public SettingPage(MyFinancePage myFinancePage) {
		// TODO Auto-generated constructor stub
		super(myFinancePage);
	}
	
	/**
	 * 重置支付密码
	 * @return 
	 */
	public ResetPayPwdPage resetPayPwd(){
		String id="resetPaypwd";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("payPassword"));
		return new ResetPayPwdPage(this);
	}

	/**
	 * 重置登录密码
	 * @return 
	 */
	public ResetLoginPwdPage resetLoginPwd(){
		String id="resetLogin";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("oldpwd"));
		return new ResetLoginPwdPage(this);
	}
}
