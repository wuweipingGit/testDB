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
	CharSequence subTitle="���Ÿ�����Ƶ���߹ۿ�_hao123Ӱ��";
	LoginECShop loginECShop;
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
  
	/*
	 * ����ҳ�������ת����Ӧ�Ĵ���
	 * author:��ΰƽ
	 */
  //@Test
  public void testMethod() throws InterruptedException {
	  //��Ĭ��ҳ��
	  driver.get(url1);
	  //�����������
	  WebElement linkTieba=driver.findElement(By.cssSelector("body > div.content > div.clearfix.content-top.page-width > div.main.ovh > div.sites.mod.mt10 > ul > li:nth-child(1) > a:nth-child(2)"));
	  linkTieba.click();
	  //��ʱĬ�ϴ�����Ȼ��urlָ������ҳ�����Ӱ������
	  WebElement linkMovie=driver.findElement(By.cssSelector("body > div.content > div.clearfix.content-top.page-width > div.main.ovh > div.cools.mod.clearfix.mt10 > div.group.clearfix.first-group > div:nth-child(1) > span.fl > a"));
	  linkMovie.click();
	  //������д��ھ��
	  Set<String> handles=driver.getWindowHandles();
	  //���������������ҳ������ת����Ӧ��ҳ��
	  for(String h:handles) {
		  driver.switchTo().window(h);
		  System.out.println(driver.getTitle());
		  //�����ǰ���ڵı������ָ�����ַ��������ڴ˴��ڲ���ҳ��Ԫ��
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
   * ��¼ecshop,�����û����Ľ��û��Ա��޸ĳ���
   * author:��ΰƽ
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
	  //�жϵ�ѡ��ť"��"�Ƿ�ѡ�У����ûѡ�У�����ѡ��
	  if(!isMale.isSelected()) {
		  isMale.click();
	  }
	  //�ж��Ƿ���ѡ�У����ûѡ�У��򱨴�
	  assertTrue(isMale.isSelected());
  }
}
