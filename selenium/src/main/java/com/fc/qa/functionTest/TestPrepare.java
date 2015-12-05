package com.fc.qa.functionTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestPrepare {
	
	//启动driver
	public WebDriver getDriver() {
		WebDriver driver = new ChromeDriver();
		//启动drvier
		String dir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", dir + "/src/test/resource/chromedriver.exe");
		return driver;
	}

	//读配置文件
	public Properties getConfig() {
		//获取properties
		Properties p = new Properties();
		InputStream in = Object.class.getResourceAsStream("/com/fc/qa/functionTest/config.properties");
		try {
			p.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	//写配置文件
	public void setConfig(Properties p) {
		//获取properties
		p = getConfig();
		try {
			//			InputStream in = new FileInputStream("config.properties");
			//			p.load(in);
			FileOutputStream out = new FileOutputStream("config.properties");
			//			p.put(key, value);
			//			p.setProperty(key, value);
			p.store(out, "update");
			//			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	//登录
	public void login(WebDriver driver, String clientId, String password) throws InterruptedException {
		Properties p = getConfig();
		driver.get(p.getProperty("ip") + "/Dashboard/user/login.html");
		driver.findElement(By.id("clientId")).sendKeys(clientId);
		driver.findElement(By.id("clientSecret")).sendKeys(password);
		driver.findElement(By.id("loginNormalBtn")).click();
		Thread.sleep(2000);
		return;
	}

	//支付
	public void payment(WebDriver driver) throws InterruptedException {
		//支付
		driver.findElement(By.id("confirmPayBtn")).click();
		Thread.sleep(2000);
		//输入支付密码
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.id("payPassword"))).sendKeys("111111").perform();
		//确定
		driver.findElement(By.id("confirmPwdBtn")).click();
		Thread.sleep(20000);
		//支付完成
		driver.findElement(By.linkText("完成")).click();
		Thread.sleep(2000);
		//跳转至产品类型列表
		return;
	}

	//选择如意宝
	public void chooseRyb(WebDriver driver) throws InterruptedException {
		//产品类型列表
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		//如意宝列表，选择某一款产品
		driver.findElement(By.xpath("//*[@id='section1']/dl[1]/dd[2]/a")).click();
		Thread.sleep(2000);
		//立即投资
		driver.findElement(By.id("investBtn")).click();
		Thread.sleep(2000);
		//选择份数
		driver.findElement(By.cssSelector(".prod-pay-num-add")).click();
		//创建订单
		driver.findElement(By.id("toCreatOrder")).click();
		Thread.sleep(2000);
		//跳转至支付页
		return;
	}

	//银行卡支付
	public void paymentBank(WebDriver driver) throws InterruptedException {
		//更换银行卡
		driver.findElement(By.id("canUseBank")).click();
		Thread.sleep(2000);
		//选择更换
		driver.findElement(By.xpath("//*[@id='selPayment']/div[3]/span")).click();
		Thread.sleep(2000);
		//选择第二张银行卡
		driver.findElement(By.xpath("//*[@id='paymentList']/li[2]/div[2]")).click();
		Thread.sleep(2000);
		//调用支付密码
		payment(driver);
		return;
	}

	//易钱包支付
	public void paymentYqb(WebDriver driver) throws InterruptedException {
		payment(driver);
		return;
	}

}
