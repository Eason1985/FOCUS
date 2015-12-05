package com.fc.qa.functionTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.jetty.util.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.ClassPathResource;
import org.openqa.selenium.server.SeleniumServer;
import org.seleniumhq.jetty7.io.Connection;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.thoughtworks.selenium.Selenium;

public class StringTest {
	private final Properties in;
	private final TestPrepare testPrepare;
	public  StringTest() {
		testPrepare=new TestPrepare();
		in=testPrepare.getConfig();
	}
	
	public void testHello(){
		System.out.print(in.getProperty("y"));
		in.setProperty("x","bvn");
//		testPrepare.setConfig("x", "5");
		testPrepare.setConfig(in);
		
		System.out.println(in.getProperty("x"));
		
		/*
		//获取properties
				Properties p = new Properties();
				try {
					InputStream in = new FileInputStream("config.properties");
					p.load(in);
				    System.out.print(p.getProperty("x"));
					FileOutputStream  out = new FileOutputStream("config.properties");
					p.setProperty("x", "3");
					p.store(out, "update");
//					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
		
	}
	public static void main(String[] args)  {
		StringTest stringTest=new StringTest();
		stringTest.testHello();
	}

}
