package com.test.DB;

import org.testng.annotations.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.test.fengzhuang.Fengzhuang;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

//import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestMysql {
	
	private Connection conn = null; 
	private String url = "jdbc:mysql://localhost:3306/test"; 
	private String driverClassName = "com.mysql.jdbc.Driver"; 
	private String user = "root"; 
	private String pass = ""; 
	ChromeDriver driver;
	String qqurl;
	String arrLength1="";
	String arrLength2="";
	Fengzhuang fz=new Fengzhuang();
	String sqlStr = "select * from account"; 
	String[] account={"username","password"};
	
	
	@BeforeSuite(groups= {"group0"})
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		qqurl="http://www.qq.com";
		driver=new ChromeDriver();
	}
	
    @Test(dataProvider="dp")
    public void testMysql(String username,String password) throws InterruptedException {
	   driver.get(qqurl);
	   WebElement login=driver.findElement(By.cssSelector("#loginGrayIcon > a[id=\"loginGrayLayout\"]"));
	   login.click();
	   Thread.sleep(2000);
	   driver.switchTo().frame("ui_ptlogin");
	   WebElement login_style=driver.findElement(By.cssSelector("#switcher_plogin"));
	   login_style.click();
	   WebElement input_u=driver.findElement(By.id("u"));
	   input_u.clear();
	   input_u.sendKeys(username);
	   WebElement input_p=driver.findElement(By.id("p"));
	   input_p.clear();
	   input_p.sendKeys(password);
	   WebElement button=driver.findElement(By.cssSelector("#login_button"));
	   button.click();
	   
    }
    @BeforeClass
    public void beforeClass() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	    Class.forName(driverClassName).newInstance(); 
	    conn = (Connection) DriverManager.getConnection(url, user, pass); 
    }

    @AfterClass
    public void afterClass() throws InterruptedException{
		  conn = null; 
		  Thread.sleep(5000);
		  driver.quit();
    }

   	@DataProvider(name="dp")
  	public Object[][] provideDate() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		System.out.println("test Git");
		System.out.println("add this line by wuweipingGit34 local");
  		return fz.getParametersFromDB(driverClassName,url,conn,user,pass,sqlStr,account);
  		
  	}
}
