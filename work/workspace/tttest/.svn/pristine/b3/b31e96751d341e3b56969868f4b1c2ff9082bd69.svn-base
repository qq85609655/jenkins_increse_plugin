package com.unicompayment.fip.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringACUtil implements ApplicationContextAware {

	/**
	 * 当前IOC
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 设置当前上下文环境，此方法由spring自动装配
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		applicationContext = ac;
	}

	/**
	 * 从当前IOC获取bean
	 * 
	 * @param <T>
	 * 
	 * @param id
	 *            bean的id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String id, Class<T> clazz) {
		Object object = applicationContext.getBean(id);
		return (T) object;
	}

	/**
	 * 从当前IOC获取bean
	 * 
	 * @param <T>
	 * 
	 * @param id
	 *            bean的id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		Object object = applicationContext.getBean(clazz);
		return (T) object;
	}
}
