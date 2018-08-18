package com.test.testSeleniumMethod;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.test.fengzhuang.LoginECShop;


public class TestSeleniumMethod {

	ChromeDriver driver;
	String url1="http://site.baidu.com/";
	String url2="http://localhost:81/ecshop";
	CharSequence subTitle="热门高清视频在线观看_hao123影视";
	LoginECShop loginECShop;
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
  
	/*
	 * 根据页面标题跳转到对应的窗口
	 * author:吴伟平
	 */
  //@Test
  public void testMethod() throws InterruptedException {
	  //打开默认页面
	  driver.get(url1);
	  //点击贴吧链接
	  WebElement linkTieba=driver.findElement(By.cssSelector("body > div.content > div.clearfix.content-top.page-width > div.main.ovh > div.sites.mod.mt10 > ul > li:nth-child(1) > a:nth-child(2)"));
	  linkTieba.click();
	  //此时默认窗口仍然是url指定的网页，点击影视链接
	  WebElement linkMovie=driver.findElement(By.cssSelector("body > div.content > div.clearfix.content-top.page-width > div.main.ovh > div.cools.mod.clearfix.mt10 > div.group.clearfix.first-group > div:nth-child(1) > span.fl > a"));
	  linkMovie.click();
	  //获得所有窗口句柄
	  Set<String> handles=driver.getWindowHandles();
	  //遍历句柄，根据网页标题跳转到相应的页面
	  for(String h:handles) {
		  driver.switchTo().window(h);
		  System.out.println(driver.getTitle());
		  //如果当前窗口的标题包含指定的字符串，则在此窗口操作页面元素
		  if(driver.getTitle().contains(subTitle)) {
			  System.out.println(driver.getTitle());
			  WebElement gaoxiao=driver.findElement(By.cssSelector("#erjiV2Header > div.header > div > div.v2-tools > div.login > a > em"));
			  gaoxiao.click();
			  
			  break;
		  }else {
			  Thread.sleep(2000);
			  driver.close();
		  }
	  }
  }
  /*
   * 登录ecshop,进入用户中心将用户性别修改成男
   * author:吴伟平
   */
  @Test
  public void modifySex() throws InterruptedException {
	  loginECShop=new LoginECShop("wuweiping","wwp1234588888",driver,url2);
	  loginECShop.loginECShop();
	  Thread.sleep(2000);
	  WebElement usercenter=driver.findElement(By.cssSelector("#ECS_MEMBERZONE > font > a:nth-child(2)"));
	  usercenter.click();
	  WebElement userinfo=driver.findElement(By.cssSelector("body > div:nth-child(8) > div.AreaL > div > div > div > div > a:nth-child(2)"));
	  userinfo.click();
	  Thread.sleep(2000);
	  WebElement isMale=driver.findElement(By.cssSelector("body > div:nth-child(8) > div.AreaR > div > div > div > form:nth-child(4) > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=\"radio\"]:nth-child(2)"));
	  //判断单选按钮"男"是否选中，如果没选中，则点击选中
	  if(!isMale.isSelected()) {
		  isMale.click();
	  }
	  //判断是否已选中，如果没选中，则报错
	  assertTrue(isMale.isSelected());
  }
}
