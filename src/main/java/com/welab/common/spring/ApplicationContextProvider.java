
package com.welab.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextProvider implements ApplicationContextAware
{
	private static ApplicationContext s_applicationContext;
	
	/** 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		s_applicationContext = context;
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return s_applicationContext;
	}

}
