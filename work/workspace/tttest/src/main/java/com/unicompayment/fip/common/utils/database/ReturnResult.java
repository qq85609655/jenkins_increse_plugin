/* * @(#)ReturnResult.java    V1.0.0      @2014-6-18 * * Project:unpcommon * * Modify Information: *    Author        Date        Description *    ============  ==========  ======================================= *    wangchangwei       2014-6-18     Create this file *  * Copyright Notice: *     Copyright (c) 2009-2014 Unicompay Co., Ltd.  *     1002 Room, No. 133 North Street, Xi Dan,  *     Xicheng District, Beijing ,100032, China  *     All rights reserved. * *     This software is the confidential and proprietary information of *     Unicompay Co., Ltd. ("Confidential Information"). *     You shall not disclose such Confidential Information and shall use  *     it only in accordance with the terms of the license agreement you  *     entered into with Unicompay. */package com.unicompayment.fip.common.utils.database;/** * 返回结果 *  * @author: wangchangwei *  */public class ReturnResult {	private String	code;	private String	mess;	private Object	value;	private Integer flag;			//负数为失败，0为未知，正数为成功		public ReturnResult(String code) {		super();		this.code = code;	}		public ReturnResult(String code, String mess) {		super();		this.code = code;		this.mess = mess;	}	public ReturnResult(String code, String mess, Object value) {		super();		this.code = code;		this.mess = mess;		this.value = value;	}		public ReturnResult(String code, String mess, Integer flag) {		super();		this.code = code;		this.mess = mess;		this.flag = flag;	}		public ReturnResult(String code, String mess, Object value, Integer flag) {		super();		this.code = code;		this.mess = mess;		this.value = value;		this.flag = flag;	}	public Integer getFlag() {		return flag;	}	public void setFlag(Integer flag) {		this.flag = flag;	}	public String getCode() {		return code;	}		public void setCode(String code) {		this.code = code;	}		public String getMess() {		return mess;	}	public void setMess(String mess) {		this.mess = mess;	}	public Object getValue() {		return value;	}		@SuppressWarnings("unchecked")	public <T> T fetchValue() {		return (T) value;	}		public void setValue(Object value) {		this.value = value;	}}