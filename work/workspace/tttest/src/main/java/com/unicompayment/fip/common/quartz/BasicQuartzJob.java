/*
 * @(#)BasicQuartzJob.java     V1.0.0      @2014-6-18
 *
 * Project:unpcommon
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    chenyong       2014-6-18     Create this file
 * 
 * Copyright Notice:
 *     Copyright (c) 2009-2014 Unicompay Co., Ltd. 
 *     1002 Room, No. 133 North Street, Xi Dan, 
 *     Xicheng District, Beijing ,100032, China 
 *     All rights reserved.
 *
 *     This software is the confidential and proprietary information of
 *     Unicompay Co., Ltd. ("Confidential Information").
 *     You shall not disclose such Confidential Information and shall use 
 *     it only in accordance with the terms of the license agreement you 
 *     entered into with Unicompay.
 */
package com.unicompayment.fip.common.quartz;


import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;


/**
 * quartzJob的超类，系统中需要用到job的地方集成本类可以简化开发与配置.
 * 
 * @author chenyong
 */
public abstract class BasicQuartzJob extends CronTriggerFactoryBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3022795076994102549L;
	/**
	 * 是否允许上一任务未完成时同步启动新任务
	 */
	private String concurrent;

	/**
	 * @param cronExpression
	 * 因为job任务的运行时间正则式是必须配置的，所以在正则式set方法中对job任务必须的环境参数进行设置
	 */
	public final void setCronExpression(String cronExpression) {
		try {
			super.setCronExpression(cronExpression);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MethodInvokingJobDetailFactoryBean mjb = new MethodInvokingJobDetailFactoryBean();
		mjb.setName(this.getClass().getName());
		mjb.setGroup("jobDetail");
		
		
		mjb.setTargetObject(this);
		mjb.setTargetMethod("execute");
		boolean concu = false;
		try{
			concu = Boolean.parseBoolean(concurrent);
		}catch(Exception e){
		}
		mjb.setConcurrent(concu);
		try {
			mjb.afterPropertiesSet();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setGroup(Scheduler.DEFAULT_GROUP);
		this.setName(this.getClass().getName());
		
		
		
		JobDetail jd = mjb.getObject();
		
		this.setJobDetail(jd);
		this.afterPropertiesSet();
		
//		CronTrigger ct = this.getObject();
//		ct.setJobName(mjb.getName());
//		ct.setJobGroup("cronTrigger");
		
		super.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
	}
	
	public void setConcurrent(String concurrent){
		this.concurrent = concurrent;
	}
	
	public abstract void execute();

}
