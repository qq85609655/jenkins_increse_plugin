/*
 * @(#)SQLSymbol.java    V1.0.0      @2014-6-18
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
 * 操作符
 * 
 * @author: wangchangwei
 * 
 */
public abstract class SQLSymbol {

	/**
	 * 等于
	 */
	public static final String EQ = "=";
	/**
	 * 小于
	 */
	public static final String LT = "<";
	/**
	 * 大于
	 */
	public static final String GT = ">";
	/**
	 * 大于等于
	 */
	public static final String GTE = ">=";
	/**
	 * 小于等于
	 */
	public static final String LTE = "<=";
	/**
	 * 非等于
	 */
	public static final String NOT = "!=";

	public static final String DEFAULT_SYMBOL = SQLSymbol.EQ;

	public static String obtainFirstSymbol(String... symbol) {
		if (null == symbol || symbol.length <= 0) {
			return DEFAULT_SYMBOL;
		}
		return symbol[0];
	}
}