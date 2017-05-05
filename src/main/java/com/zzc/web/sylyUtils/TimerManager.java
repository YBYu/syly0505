package com.zzc.web.sylyUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zzc.web.htoal.controller.SyncHotelController;
import com.zzc.web.scenicmanage.controller.SynchroScenicController;
import com.zzc.web.travel.controller.SyncTravelController;
  
/** 
 * 任务管理 
 * 
 */

public class TimerManager implements ServletContextListener{  
  
    //时间间隔(一天)  
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	
    //时间间隔(一小时)  
//	private static final long PERIOD_DAY = 60 * 60 * 1000;
    
    @Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO 自动生成的方法存根
		Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        Date date=calendar.getTime(); //第一次执行定时任务的时间  
        
        // 设定执行时间
        if (date.before(new Date())) {  
            date = this.addDay(date, 1);  
        }
//		SyncTravelController.sendTravelInfo();
//		SyncTravelController.syncTravelInfo();
//		SyncTravelController.sendFinance();
//		SyncTravelController.syncFinance();
//		SyncTravelController.sendQuarter();
//		SyncTravelController.syncQuarter();
//		SyncHotelController.sendHotelAnnual();
//		SyncHotelController.syncHotelAnnual();
//		SyncHotelController.sendHotelQuarter();
//		SyncHotelController.syncHotelQuarter();
//		SyncHotelController.sendHotelMonthly();
//		SyncHotelController.syncHotelMonthly();
//		SyncHotelController.sendHotelInfo();
//		SyncHotelController.syncHotelInfo();
        Timer timer1 = new Timer();  
        timer1.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.sendTravelInfo();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer2 = new Timer();  
        timer2.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.syncTravelInfo();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer3 = new Timer();  
        timer3.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.sendFinance();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer4 = new Timer();  
        timer4.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.syncFinance();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer5 = new Timer();  
        timer5.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.sendQuarter();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer6 = new Timer();  
        timer6.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncTravelController.syncQuarter();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer7 = new Timer();  
        timer7.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.sendHotelAnnual();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer8 = new Timer();  
        timer8.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.syncHotelAnnual();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer9 = new Timer();  
        timer9.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.sendHotelQuarter();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer10 = new Timer();  
        timer10.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.syncHotelQuarter();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer11 = new Timer();  
        timer11.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.sendHotelMonthly();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer12 = new Timer();  
        timer12.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.syncHotelMonthly();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer13 = new Timer();  
        timer13.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.sendHotelInfo();
			}
        	
        },date,PERIOD_DAY);
        
        Timer timer14 = new Timer();  
        timer14.schedule(new TimerTask(){
			@Override
			public void run() {
				SyncHotelController.syncHotelInfo();
			}
        	
        },date,PERIOD_DAY);
        

        
        
        Calendar calendar1 = Calendar.getInstance();  
      calendar1.set(Calendar.HOUR_OF_DAY, 3); //凌晨3点  
      calendar1.set(Calendar.MINUTE,30);  
      calendar1.set(Calendar.SECOND, 0);  
      Date date1=calendar1.getTime(); //第一次执行定时任务的时间  
      
      if(date1.before(new Date())){
    	  date1 = this.addDay(date1, 1);
      }
      
      Timer timer15 = new Timer();  
      timer15.schedule(new TimerTask(){
			@Override
			public void run() {
				SynchroScenicController.saveScenicInfo();
			}
      	
      },date1,PERIOD_DAY);


      Timer timer16= new Timer();  
      timer16.schedule(new TimerTask(){
			@Override
			public void run() {
				SynchroScenicController.scenicAudit();
			}
      	
      },date1,PERIOD_DAY);
      
      
      Timer timer17 = new Timer();  
      timer17.schedule(new TimerTask(){
			@Override
			public void run() {
				try {
					SynchroScenicController.sendScenicQuarterly();
				} catch (UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
      	
      },date1,PERIOD_DAY);
      
      
      Timer timer18= new Timer();  
      timer18.schedule(new TimerTask(){
			@Override
			public void run() {
				SynchroScenicController.scenicQuarterAudit();
			}
      	
      },date1,PERIOD_DAY);
      
      
      Timer timer19 = new Timer();  
      timer19.schedule(new TimerTask(){
			@Override
			public void run() {
				try {
					SynchroScenicController.sendScenicAnnual();
				} catch (UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
      	
      },date1,PERIOD_DAY);
      
      Timer timer20 = new Timer();  
      timer20.schedule(new TimerTask(){
			@Override
			public void run() {
				SynchroScenicController.scenicAnnualAudit();
			}
      	
      },date1,PERIOD_DAY);

      
      Timer timer21 = new Timer();  
      timer21.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.sendHotelEstimate();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
      Timer timer22 = new Timer();  
      timer22.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.sendOnBuiness();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
      Timer timer23 = new Timer();  
      timer23.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.addTourism();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
      Timer timer24 = new Timer();  
      timer24.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.editTourism();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
      Timer timer25 = new Timer();  
      timer25.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.addDragonBoatFestival();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
      Timer timer26 = new Timer();  
      timer26.schedule(new TimerTask(){
  		@Override
  		public void run() {
  			try {
				SyncHotelController.addMoonFestival();
			} catch (Exception e) {
				e.printStackTrace();
			}
  		}
      	
      },date,PERIOD_DAY);
      
	} 

	
    // 增加或减少天数  
    public Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }
    // 增加或减少小时  
    public Date addHour(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.HOUR_OF_DAY, num);  
        return startDT.getTime();  
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO 自动生成的方法存根
		
	}
  
}  