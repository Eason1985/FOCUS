package com.fc.focus.selenium.pages;

import com.fc.focus.selenium.utils.DriverBackedSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 登录页面
 * @author sunshine
 *
 */

public class LoginPage extends BasePageObject{
	TestPrepare testPrepare;
	
	public LoginPage(WebDriver driver, String baseURL){
		super(driver);
		this.baseUrl = baseURL;
	}
	public LoginPage(DriverBackedSelenium selenium){
		super(selenium);
	}
	/**
	 * 登录
	 * @param clientId
	 * @param password
	 * @return
	 * @throws InterruptedException
	 */
	public  MyFinancePage login(String clientId,String password) throws InterruptedException {
		driver.get(baseUrl+"/Dashboard/user/login.html");
		driver.findElement(By.id("clientId")).sendKeys(clientId);
		driver.findElement(By.id("clientSecret")).sendKeys(password);
		driver.findElement(By.id("loginNormalBtn")).click();
		waitForLoad(By.id("realName"));
		return new MyFinancePage(this);
	}
	/**
	 * 忘记密码
	 * @return
	 */
	public ForgetPasswordPage forgetPassword(){
		driver.get(baseUrl+"/Dashboard/user/login.html");
		String id="forgetpwd";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("iponeinfo"));
		return new ForgetPasswordPage(this);
	}
	
	public RegisterPage register(){
		driver.get(baseUrl+"/Dashboard/user/login.html");
		String xpath="/html/body/div/div[2]/div/div[1]/div[6]/a";
		driver.findElement(By.xpath(xpath)).click();
		waitForLoad(By.id("selDocuments"));
		return new RegisterPage(this);
	}
 
}
