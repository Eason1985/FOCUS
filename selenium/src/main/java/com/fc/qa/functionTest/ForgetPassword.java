package com.fc.qa.functionTest;

import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPassword {
	private final WebDriver webDriver;
	private final Properties properties;
	private final TestPrepare testPrepare;
	
	public ForgetPassword(){
		testPrepare=new TestPrepare();
		webDriver=testPrepare.getDriver();
		properties=testPrepare.getConfig();
	}

	public void passwordReset() throws InterruptedException {
		
		//登录首页
		webDriver.get(properties.getProperty("ip")+"/Dashboard/user/login.html");
		//忘记密码
		webDriver.findElement(By.id("forgetpwd")).click();
		Thread.sleep(2000);
		//输入手机号、获取验证码
		webDriver.findElement(By.id("phonenum")).sendKeys("18200300000");
		webDriver.findElement(By.id("getCode")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id("smsCaptcha")).sendKeys("888888");
		webDriver.findElement(By.id("authCellBtn")).click();
		Thread.sleep(2000);
		//输入新登录密码
		webDriver.findElement(By.id("firstpwd")).sendKeys("12345671a");
		webDriver.findElement(By.id("secondpwd")).sendKeys("12345671a");
		webDriver.findElement(By.id("confirmResePwdBtn")).click();
		Thread.sleep(2000);
		//我知道了
		webDriver.findElement(By.id("iKnow")).click();
		Thread.sleep(2000);
	}
	
	public static void main( String[] args ) throws IOException, InterruptedException {
		ForgetPassword forgetPassword = new ForgetPassword();
		forgetPassword.passwordReset();
	}
}
