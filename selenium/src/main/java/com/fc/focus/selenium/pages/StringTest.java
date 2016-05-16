package com.fc.focus.selenium.pages;

import java.util.Properties;

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
