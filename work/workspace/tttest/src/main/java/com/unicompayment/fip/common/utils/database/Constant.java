/*
 * @(#)Constant.java    V1.0.0      @2014-6-18
 *
 * Project:unpcommon
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    wangchangwei       2014-6-18     Create this file
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
package com.unicompayment.fip.common.utils.database;

/**
 * 常量类
 * 
 * @author: wangchangwei
 * 
 */
public abstract class Constant {

	/**
	 * 换行符
	 */
	public static final String LINE_SEPARATOR = System.getProperty(
			"line.separator", "/r/n");

	/**
	 * 默认编码字集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 *
	 */
	public static final String GBK = "GBK";

	/**
	 * (批量新增和批量更新时)多少个数量级,flush一次
	 */
	public static final Integer FLUSH_CRITICAL_VAL = 100;

	/**
	 * (批量新增和批量更新时)大数量级,flush一次
	 */
	public static final Integer FLUSH_BIG_CRITICAL_VAL = 99999;

	/**
	 * 批量删除时,多少个数量级,重新使用or连接 Oracle IN语句的最大表达式数为 1000
	 */
	public static final Integer DELETE_CRITICAL_VAL = 999;

	/**
	 * varchar2
	 */
	public static final Integer JDBC_VARCHAR2_MAXLEN = 2000;
}