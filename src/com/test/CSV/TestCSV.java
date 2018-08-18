package com.test.CSV;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;
import com.test.fengzhuang.Fengzhuang;

public class TestCSV {
	ChromeDriver driver;
	Fengzhuang fz=new Fengzhuang();
	String url="http://localhost:81/ecshop/";
	String filepath="D:\\account.csv";
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
  @Test(dataProvider="dp")
  public void test(String un,String pw) {
	  driver.get(url);
	  WebElement loginBtn=fz.getElement("#ECS_MEMBERZONE > a:nth-child(2) > img", "cssSelector", driver);
	  loginBtn.click();
	  WebElement inputUsername=fz.getElement("username", "name", driver);
	  inputUsername.sendKeys(un);
	  WebElement inputPassword=fz.getElement("password", "name", driver);
	  inputPassword.sendKeys(pw);
	  WebElement inputSubmit=fz.getElement("submit", "name", driver);
	  inputSubmit.click();
  }
  
  //设计方法，给test方法提供参数
  @DataProvider(name="dp")
  public Object[][] dataProvider() throws IOException {
	  return fz.getParametersFromCSV(filepath);
  }
}


