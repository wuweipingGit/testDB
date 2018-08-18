package com.test.fengzhuang;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Fengzhuang {
	
		  /*
		   * 此方法用于定位元素，并返回页面元素对象，参数含义：
		   * path:页面元素的属性值，如id，name,classname,xpath的值等
		   * type:页面元素定位方式
		   * dv：浏览器驱动对象
		   * author:吴伟平
		   */
		  public WebElement getElement(String path,String type,ChromeDriver dv) {
			  WebElement wb;
			  if(type=="cssSelector") {
				  wb=dv.findElement(By.cssSelector(path));
				  return wb;
			  }else if(type=="Xpath") {
				  wb=dv.findElement(By.xpath(path));
			  }else if(type=="id") {
				  wb=dv.findElement(By.id(path));
				  return wb;
			  }else if(type=="className") {
				  wb=dv.findElement(By.className(path));
				  return wb;
			  }else {
				  wb=dv.findElement(By.name(path));
				  //return wb;
			  }
			return wb;
		  }

		  /*
		   * 设计方法getParameters,用来读取参数文件，返回一个二维数组,参数含义：
		   * filename:存放数据的文件路径
		   * author:吴伟平
		   */
		  public Object[][] getParametersFromCSV(String filename) throws IOException{
			  List<Object[]> records=new ArrayList<Object[]>();
			  String record;
			  /*
			   * BufferedReader 由Reader类扩展而来，提供通用的缓冲方式文本读取，而且提供了很实用的readLine，读取一个文本行，
			   * 从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取。
			   *一般用法：
			   *BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("ming.txt")));
			   */
			  BufferedReader  file=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
			  file.readLine();
			  while((record=file.readLine())!=null) {
				  String fields[]=record.split(",");
				  records.add(fields);
			  }
			  file.close();
			  Object[][] result=new Object[records.size()][];
			  for(int i=0;i<records.size();i++) {
				  result[i]=records.get(i);
			  }
			  return result;
		  }
	  
		  /*
		   *此方法的功能是根据传入的参数从数据库获取相应的数据，参数含义： 
		   *driverClassName：数据库驱动类名
		   *dbUrl：数据库连接字符串
		   *conn：数据库连接对象
		   *user：数据库用户名
		   *password：数据库用户密码
		   *sqlStr：sql查询语句
		   *strArr：用于存储要查询的表字段名称的字符串数组
		   *author:吴伟平
		   */
		  public Object[][] getParametersFromDB(String driverClassName,String dbUrl,Connection conn,String user,String password,String sqlStr,String[] strArr) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			  List<Object[]> records=new ArrayList<Object[]>();//接收非确定长度的数组
			  Class.forName(driverClassName).newInstance();//加载数据库驱动类
			  conn=(Connection) DriverManager.getConnection(dbUrl, user, password);//连接数据库
			  Statement st=(Statement) conn.createStatement();
			  ResultSet result=st.executeQuery(sqlStr);//执行sql语句
			  
			  int j=0;
			  while(result.next()) {
				  String[] arr=new String[strArr.length];//用于存放从数据库查询的一条结果
				  for(int i=0;i<strArr.length;i++) {
					  arr[i]=result.getString(strArr[i]).toString();//将查询的结果作为元素放到数组中
					  System.out.println(arr[i]);
				  }
				  records.add(arr);//将每条结果组成的数组添加到列表中
			  }
			  Object[][] obj=new Object[records.size()][];
			  for(int i=0;i<records.size();i++) {
				  obj[i]=records.get(i);//将列表转换成Object二维数组
			  }
			  return obj;
		  }
}
