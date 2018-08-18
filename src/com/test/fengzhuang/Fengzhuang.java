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
		   * �˷������ڶ�λԪ�أ�������ҳ��Ԫ�ض��󣬲������壺
		   * path:ҳ��Ԫ�ص�����ֵ����id��name,classname,xpath��ֵ��
		   * type:ҳ��Ԫ�ض�λ��ʽ
		   * dv���������������
		   * author:��ΰƽ
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
		   * ��Ʒ���getParameters,������ȡ�����ļ�������һ����ά����,�������壺
		   * filename:������ݵ��ļ�·��
		   * author:��ΰƽ
		   */
		  public Object[][] getParametersFromCSV(String filename) throws IOException{
			  List<Object[]> records=new ArrayList<Object[]>();
			  String record;
			  /*
			   * BufferedReader ��Reader����չ�������ṩͨ�õĻ��巽ʽ�ı���ȡ�������ṩ�˺�ʵ�õ�readLine����ȡһ���ı��У�
			   * ���ַ��������ж�ȡ�ı�����������ַ����Ӷ��ṩ�ַ���������еĸ�Ч��ȡ��
			   *һ���÷���
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
		   *�˷����Ĺ����Ǹ��ݴ���Ĳ��������ݿ��ȡ��Ӧ�����ݣ��������壺 
		   *driverClassName�����ݿ���������
		   *dbUrl�����ݿ������ַ���
		   *conn�����ݿ����Ӷ���
		   *user�����ݿ��û���
		   *password�����ݿ��û�����
		   *sqlStr��sql��ѯ���
		   *strArr�����ڴ洢Ҫ��ѯ�ı��ֶ����Ƶ��ַ�������
		   *author:��ΰƽ
		   */
		  public Object[][] getParametersFromDB(String driverClassName,String dbUrl,Connection conn,String user,String password,String sqlStr,String[] strArr) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			  List<Object[]> records=new ArrayList<Object[]>();//���շ�ȷ�����ȵ�����
			  Class.forName(driverClassName).newInstance();//�������ݿ�������
			  conn=(Connection) DriverManager.getConnection(dbUrl, user, password);//�������ݿ�
			  Statement st=(Statement) conn.createStatement();
			  ResultSet result=st.executeQuery(sqlStr);//ִ��sql���
			  
			  int j=0;
			  while(result.next()) {
				  String[] arr=new String[strArr.length];//���ڴ�Ŵ����ݿ��ѯ��һ�����
				  for(int i=0;i<strArr.length;i++) {
					  arr[i]=result.getString(strArr[i]).toString();//����ѯ�Ľ����ΪԪ�طŵ�������
					  System.out.println(arr[i]);
				  }
				  records.add(arr);//��ÿ�������ɵ�������ӵ��б���
			  }
			  Object[][] obj=new Object[records.size()][];
			  for(int i=0;i<records.size();i++) {
				  obj[i]=records.get(i);//���б�ת����Object��ά����
			  }
			  return obj;
		  }
}
