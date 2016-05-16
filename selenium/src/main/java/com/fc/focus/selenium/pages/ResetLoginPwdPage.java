package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 重置登录页面
 * @author Administrator
 *
 */

public class ResetLoginPwdPage extends BasePageObject{

	public ResetLoginPwdPage(SettingPage settingPage) {
		// TODO Auto-generated constructor stub
		super(settingPage);
	}
	
	public void inputOldPwd(String oldPwd){
		String id="oldpwd";
		driver.findElement(By.id(id)).sendKeys(oldPwd);
	}
	
	public void nextStep(){
		String id="authLoginPwdBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("firstpwd"));
	}
	
	public void inputFirstpwd(String firstpwd){
		String id="firstpwd";
		driver.findElement(By.id(id)).sendKeys(firstpwd);
	}
	
	public void inputSecondpwd(String secondpwd){
		String id="secondpwd";
		driver.findElement(By.id(id)).sendKeys(secondpwd);
	}
	
	public void confirm(){
		String id="confirmResePwdBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("iKnow"));
	}
	
	public MyFinancePage iKnow(){
		String id="iKnow";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("toMyProfile"));
		return new MyFinancePage(this);
	}

}
