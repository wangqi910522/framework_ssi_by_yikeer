package com.ht.yikecrm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.ht.yikecrm.util.DateUtil;
import com.ht.yikecrm.util.IpUtil;

public class FlowHelper {
	
	/** 创建文件夹 */
	public static void createFile(String url) throws Exception{
		if(!new File(url).exists()){
			new File(url).mkdirs();//创建文件夹
		}
	}
	
	/**
	 * 记录用户访问
	 * @param flow
	 * @throws Exception
	 */
	public static void write(Flow flow) throws Exception{
		String context = null;
		String title = "访问者类型" + "," + "用户名" + "," + "姓名" + "," + "公司" + ","+ "访问模块" + "," + "访问地址" + "," + "访问IP" + "," + "访问日期\n";;
		if(!flow.getB()){//未登录用户
			context = "游客" + "," + " " + "," + " " + "," + " " + "," + " " + "," + " " + "," + IpUtil.getIpAddr(flow.getRequest()) + "," + DateUtil.timeToStr(new Date()) + "\n";
		}else{//登录用户
			context = "用户" + "," + flow.getUserName() + "," + flow.getName() + "," + flow.getCompany() + "," + flow.getDisplayName() + "," + flow.getRequest().getRequestURI() + "," + IpUtil.getIpAddr(flow.getRequest()) + "," + DateUtil.timeToStr(new Date()) + "\n";
		}
		FlowHelper.createFile(flow.getUrl());
		Calendar c = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。返回的 Calendar 基于当前时间，使用了默认时区和默认语言环境。
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		if(month.length() == 1){
			month = "0" + month;
		}
		String day = String.valueOf(c.get(Calendar.DATE));
		if(day.length() == 1){
			day = "0" + day;
		}
		String sDate = String.valueOf(c.get(Calendar.YEAR)) + month + day;
		BufferedWriter bw = null;
		String path = flow.getUrl() + "/" + sDate + "_" + flow.getType() + "_visit.csv";
		if(!new File(path).exists()){
			new File(path).createNewFile();//创建文件
			bw = new BufferedWriter(new FileWriterWithEncoding(new File(path), "GBK", true));
			bw.write(title);
		}else{
			bw = new BufferedWriter(new FileWriterWithEncoding(new File(path), "GBK", true));
		}
		bw.write(context);
		bw.close();
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime() - 24*60*60*1000L);
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		if(month.length() == 1){
			month = "0" + month;
		}
		String sDate = String.valueOf(c.get(Calendar.YEAR)) + month + String.valueOf(c.get(Calendar.DATE));
		System.out.println(sDate);
	}
}
