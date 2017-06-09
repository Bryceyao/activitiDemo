
package com.welab.common.spring;


abstract public class BeanFactory
{
	public static <T> T getBean(Class<T> classType)
	{
		return ApplicationContextProvider.getApplicationContext().getBean(classType);
	}
	
	public static <T> T getBean(String beanName, Class<T> classType)
	{
		return ApplicationContextProvider.getApplicationContext().getBean(beanName, classType);
	}
}
