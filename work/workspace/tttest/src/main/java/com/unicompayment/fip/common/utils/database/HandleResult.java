/*
 * @(#)HandleResult.java    V1.0.0      @2014-6-18
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
 * 结果状态返回
 * 
 * @author: wangchangwei
 * 
 */
public enum HandleResult {
	/**
	 * 操作成功
	 */
	UNKNOWN("", "",0),
	/**
	 * 操作成功
	 */
	SUCCESS("SUCCESS", "操作成功!",1),
	/**
	 * 操作失败
	 */
	FAILED("FAILED", "操作失败!",-1),
	/**
	 * 对象为空
	 */
	OBJECT_NULL("OBJECT_NULL", "对象为空!",-1),
	/**
	 * 非法参数
	 */
	ILLEGAL_ARGS("ILLEGAL_ARGS", "非法参数!",-1),
	/**
	 * 参数为空!
	 */
	ARGS_NULL("ARGS_NULL", "参数为空!",-1),
	/**
	 * 远程调用异常
	 */
	REMOTE_INVOKE_EXCEPTION("REMOTE_INVOKE_EXCEPTION", "远程调用异常!",-1),
	/**
	 * 系统异常
	 */
	SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统出现异常!",-1),
	/**
	 * 创建成功
	 */
	CREATE_USER_SUCCESS("CREATE_SUCCESS", "新增用户成功!",1),
	/**
	 * 更新用户成功!
	 */
	MODIFY_USER_SUCCESS("MODIFY_SUCCESS", "更新用户成功!",1),
	/**
	 * 删除用户成功!
	 */
	REMOVE_USER_SUCCESS("REMOVE_SUCCESS", "删除用户成功!",1),
	/**
	 * 更新密码成功
	 */
	MODIFY_PSWD_SUCCESS("MODIFY_PSWD_SUCCESS", "更新密码成功!",1),
	/**
	 * 更新密码失败!
	 */
	MODIFY_PSWD_FALIED("MODIFY_PSWD_FALIED", "更新密码失败!",-1),
	/**
	 * 重置密码成功
	 */
	RESET_PSWD_SUCCESS("RESET_PSWD_SUCCESS", "重置密码成功!",1),
	/**
	 * 重置密码失败
	 */
	RESET_PSWD_FAILED("RESET_PSWD_FAILED", "重置密码失败!",-1),
	/**
	 * 存在关联关系
	 */
	HAS_RELATION("HAS_RELATION", "存在关联关系!"),
	/**
	 * 已经存在
	 */
	ALREADY_EXISTS("ALREADY_EXISTS", "已经存在!",-1),
	/**
	 * 用户名或密码为空
	 */
	LOGIN_USERNAME_OR_PSWD_NULL("LOGIN_USERNAME_OR_PSWD_NULL", "用户名密码不能为空!",-1),
	/**
	 * 用户名不存在
	 */
	LOGIN_USERNAME_NOEXIST("LOGIN_USER_PSWD_ERROR", "用户名不存在!",-1),
	/**
	 * 用户名不存在
	 */
	LOGIN_PSWD_ERROR("LOGIN_PSWD_ERROR", "密码错误!",-1),
	/**
	 * 操作失败
	 */
	LOGIN_SUCCESS("LOGIN_SUCCESS", "登录成功!",1),
	/**
	 * 远程登录失败
	 */
	LOGIN_REMOTE_FAILED("LOGIN_REMOTE_FAILED", "远程登录失败!",-1),
	/**
	 * 远程登录成功
	 */
	LOGIN_REMOTE_SUCCESS("LOGIN_REMOTE_SUCCESS", "远程登录成功!",1);
	
	private ReturnResult	returnResult;
	
	private HandleResult(String code, String message) {
		this.returnResult = new ReturnResult(code, message);
	}
	
	private HandleResult(String code,String message,Integer flag){
		this.returnResult = new ReturnResult(code, message,flag);
	}
	
	private HandleResult(ReturnResult returnResult) {
		this.returnResult = returnResult;
	}
	
	public ReturnResult getReturnResult() {
		return returnResult;
	}
	
	public String getCode() {
		return this.returnResult.getCode();
	}
	
	public void setCode(String code) {
		this.returnResult.setCode(code);
	}
	
	public String getMess() {
		return this.returnResult.getMess();
	}
	
	public void setMess(String mess) {
		this.returnResult.setMess(mess);
	}
	
	public Integer getFlag(){
		return this.returnResult.getFlag();
	}
	
	public void setFlag(Integer flag){
		this.returnResult.setFlag(flag);
	}
	
	/**
	 * 成功标识大于0
	 * @return
	 */
	public Boolean isSuccess() {
		Integer flag = this.returnResult.getFlag(); 
		return  0 < flag? true : false;
	}
	
	/**
	 * 失败的标识小于0
	 * @return
	 */
	public Boolean isFailed() {
		Integer flag = this.returnResult.getFlag(); 
		return  0 > flag ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) this.returnResult.getValue();
	}
	
	public void setValue(Object value) {
		this.returnResult.setValue(value);
	}
}