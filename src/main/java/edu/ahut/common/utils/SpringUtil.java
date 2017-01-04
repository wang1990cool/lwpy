package edu.ahut.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：Spring 工具类
 * @autor:tianming
 * @date: 2015-8-5
 */
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;  
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringUtil.applicationContext = applicationContext;
	}
	/**
	 * 功能描述：根据BeanID从容器获取bean
	 * @param beanID
	 * @return
	 * @ tianming 
	 * @ 2015-8-5
	 */
	public static Object getSpringBean(String beanID){
		return applicationContext.getBean(beanID);
	}
	/**
	 * 功能描述：根据BeanID从容器获取bean
	 * @param beanID
	 * @return
	 * @ tianming 
	 * @ 2015-8-5
	 */
	public static <T> T getSpringBean(String beanID,Class<T> clazz){
		return applicationContext.getBean(beanID,clazz);
	}
	/**
	 * 功能描述：取得jdbcTemplate实例
	 * @return
	 * @ tianming 
	 * @ 2015-8-5
	 */
	public static JdbcTemplate getJdbcTemplate(){
		return  getSpringBean("jdbcTemplate",JdbcTemplate.class);
	}
	/**
	 * 功能描述：取得当前线程的HttpServletRequest
	 * @return HttpServletRequest
	 * @ tianming 
	 * @ 2015-8-5
	 */
	public static HttpServletRequest getCurrentRequest(){
		HttpServletRequest request = null;
		try {
			request = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getRequest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return request;
	}
	/**
	 * 取页面传递参数
	 * @param paramName
	 * @return
	 */
	public static String getParameter(String paramName){
		return SpringUtil.getCurrentRequest().getParameter(paramName);
	}
	/**
	 * 功能描述：取得当前线程的HttpServletResponse
	 * @return HttpServletResponse 
	 * @ tianming 
	 * @ 2015-8-5
	 */
	public static HttpServletResponse getCurrentResponse(){
		HttpServletResponse response = null;
		try {
			response = ((ServletRequestAttributes) RequestContextHolder
			        .getRequestAttributes()).getResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return response;
	}
	
	public static String getIpAddr() {
		HttpServletRequest request = SpringUtil.getCurrentRequest();
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}   
}
