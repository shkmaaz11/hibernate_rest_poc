package com.practice.hibernate.webconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author maazs
 * 
 *         Web Initializer Class-An alternative for web.xml
 * 
 */
public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	public static final Logger logger = LoggerFactory
			.getLogger(WebInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.warn("In getRootConfigClasses()");

		return new Class[] {};
	}

	/**
	 * Similar to Servlet Definition in web.xml
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.warn("In getServletConfigClasses()");

		return new Class[] { DispatcherConfig.class };
	}

	/**
	 * Similar to Servlet Mapping in web.xml
	 */
	@Override
	protected String[] getServletMappings() {
		logger.warn("In getServletMappings()");

		return new String[] { "/" };
	}

}
