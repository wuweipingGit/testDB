package com.test.fengzhuang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginECShop {
	
	private String un,pw;
	private ChromeDriver driver;
	private String url;
	public LoginECShop(String username,String password,ChromeDriver driver,String url) {
		un=username;
		pw=password;
		this.driver=driver;
		this.url=url;
	}
	
	public void loginECShop() {
		driver.get(url);
		WebElement login=driver.findElement(By.cssSelector("#ECS_MEMBERZONE > a:nth-child(2) > img"));
		  login.click();
		  WebElement username=driver.findElement(By.cssSelector("body > div.usBox.clearfix > div.usBox_1.f_l > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input"));
		  username.clear();
		  username.sendKeys("wuweiping");
		  WebElement password=driver.findElement(By.cssSelector("body > div.usBox.clearfix > div.usBox_1.f_l > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input"));
		  password.clear();
		  password.sendKeys("wwp1234588888");
		  WebElement submit=driver.findElement(By.cssSelector("body > div.usBox.clearfix > div.usBox_1.f_l > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input.us_Submit"));
		  submit.click();
	}
}
