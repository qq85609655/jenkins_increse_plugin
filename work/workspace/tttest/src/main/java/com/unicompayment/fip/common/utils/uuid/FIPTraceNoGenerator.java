package com.unicompayment.fip.common.utils.uuid;

import java.util.Date;
import java.util.Random;

import com.unicompayment.fip.common.utils.date.DateUtil;

/**
 * @类名称 FIPTraceNoGenerator
 * @作者 Vincent
 * @创建时间 2015年1月12日 下午1:44:25
 * @修改信息 2015年1月12日 下午1:44:25 by Vincent
 */
@Deprecated // using SerNoUtil
final public class FIPTraceNoGenerator {

	/**
	 * 理财交易流水号yyyyMMddHHmmss+6位随机数支付订单号，由FIP生成
	 * 
	 * @param
	 * @return
	 */
	public static final String genPayTransNo() {
		return timeAndDRandom6();
	}

	/**
	 * yyyyMMddHHmmss格式当前时间
	 * 
	 * @param
	 * @return
	 */
	private static String getTime() {
		return DateUtil.getStringDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 6位随机数
	 * 
	 * @param
	 * @return
	 */
	private static String dRandom6() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			// [0-9]
			sb.append(new Random().nextInt(10));
		}
		return sb.toString();
	}

	private static String timeAndDRandom6() {
		return new StringBuffer().append(getTime()).append(dRandom6())
				.toString();
	}

	public static void main(String[] args) {

	}

}
