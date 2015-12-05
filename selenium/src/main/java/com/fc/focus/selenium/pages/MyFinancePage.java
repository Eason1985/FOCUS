package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 我的财富页的模型定义
 * @author Administrator
 *
 */
public class MyFinancePage extends BasePageObject{
	
	/**
	 * 总资产
	 */
	private String totalAmount;
	/**
	 * 银行卡数
	 */
	private Integer bankAccountSize;
	
    public MyFinancePage(BasePageObject loginPage){
	super(loginPage);
	}
    
    /**
     *首次绑卡
     * @return
     */
    public SetPayPaswordPage firstAddBank(){
    	String id="bankCardNum";
    	driver.findElement(By.id(id)).click();
    	waitForLoad(By.id("setPayPassword"));
    	return new SetPayPaswordPage(this);
    }
    
    public BankListPage bankList(){
    	String id="bankCardNum";
    	driver.findElement(By.id(id)).click();
    	waitForLoad(By.id("bankCardList"));
    	return new BankListPage(this);
    }
	
	/**
	 * 跳转至易钱包
	 * @return
	 * @throws InterruptedException
	 */
	public YqbPage goYqb() throws InterruptedException {
		String linkText="易钱包";
		driver.findElement(By.linkText(linkText)).click();
//	    Thread.sleep(2000);
		waitForLoad(By.id("rollInBtn"));
	    return new YqbPage(this);
	}
	/**
	 * 跳转至产品类别
	 * @return
	 * @throws InterruptedException
	 */
	public ProductPage goCategory() throws InterruptedException{
		String url=baseUrl+"/category/Category.html";
		driver.get(url);
		Thread.sleep(2000);
		return new ProductPage(this);
	}
	
	/**
	 * 我的如意宝列表
	 * @return
	 */
	public MyRybListPage myRyb(){
		String xpath="/html/body/div[2]/div/div[3]/ul[1]/li[2]/a/div[2]";
		driver.findElement(By.xpath(xpath)).click();
		waitForLoad(By.id("possession"));
		return new MyRybListPage(this);
	}
	/**
	 * 我的财富资产
	 * @return
	 */
	public float currentBalance(){
		String id="asset";
    	String asset=driver.findElement(By.id(id)).getText();
    	String str = new String(asset);
    	str=str.replace(",", "");
    	return Float.parseFloat(str);
    	
    }
	
	/**
	 * 我的交易
	 * @return
	 */
	public MyTradeListPage myTrade(){
		String linkText="我的交易";
		driver.findElement(By.linkText(linkText)).click();
		waitForLoad(By.id("all"));
		return new MyTradeListPage(this);
	}
	
	/**
	 * 设置
	 * @return
	 */
  public SettingPage setting(){
	  String linkText="设置";
	  driver.findElement(By.linkText(linkText)).click();
	  waitForLoad(By.id("resetPaypwd"));
	  return new SettingPage(this);
  }
    
  /**
   * 常见问题
   * @return
   */
     public ComProblemPage commonProblem(){
    	 String linkText="常见问题";
    	 driver.findElement(By.linkText(linkText)).click();
    	 waitForLoad(By.id("qa_content"));
    	 return new ComProblemPage(this);
     }
     
     /**
      * 关于
      * @return
      */
     public AboutPage about(){
    	 String linkText="关于";
    	 driver.findElement(By.linkText(linkText)).click();
    	 waitForLoad(By.id("newVersionInfo"));
    	 return new AboutPage(this);
     }

}
