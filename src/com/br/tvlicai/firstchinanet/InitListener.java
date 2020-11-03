package com.br.tvlicai.firstchinanet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.tvlicai.firstchinanet.business.common.Util.CheckIPAddress;
import com.br.tvlicai.firstchinanet.business.service.IpRecord.IpRecordService;

public class InitListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out
		.println("-----------------项目开始运行--------------");
		//初始化定时器
		 Timer timer = new Timer();
		 
		 //延迟5000ms执行程序
		 timer.schedule(new TimerTask() {
			 @Override
			 public void run() {
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 System.out.println("当前时间" + sdf.format(new Date()));
				 //查询外网IP
				 String ip = CheckIPAddress.getIp();
				 System.out.println("当前ip" + ip);
				 IpRecordService ipRecordService = new IpRecordService();
				 int result = ipRecordService.ipRecord(ip);
				 System.out.println("ip上传结果" + result);
			 }
		 }, 0,60000);
		 
//		 Timer timer1 = new Timer();
//		 //延迟5000ms执行程序
//		 timer1.schedule(new TimerTask() {
//			 @Override
//			 public void run() {
//				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				 System.out.println("此刻时间" + sdf.format(new Date()));
//				 //查询外网IP
//				 String ip = CheckIPAddress.getIp();
//				 System.out.println("此刻ip" + ip);
//				 IpRecordService ipRecordService = new IpRecordService();
//				 int result = ipRecordService.ipRecord(ip);
//				 System.out.println("ip此刻上传结果" + result);
//			 }
//		 }, 0,40000);
	}
}
