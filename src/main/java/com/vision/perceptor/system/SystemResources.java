package com.vision.perceptor.system;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class SystemResources {
	
	static final Logger logger = LoggerFactory.getLogger(SystemResources.class);
	
	public static final String STATIC_RESOURCES_PATH = "classpath:static";
	
	@Autowired
	public ResourceLoader resourceLoader;
	
	public String getResourceFilePath(final String filePath) {	
		Resource resource = getresource(filePath);
		try {
			File file = resource.getFile();
			return file.getAbsolutePath();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
		
//		return System.getProperty("user.dir")  + "\\src\\main\\resources\\static\\images\\" + filePath;
	}
	
	public Resource getresource(final String filePath) {
		return resourceLoader.getResource(STATIC_RESOURCES_PATH + filePath);
	}
	
	public void logResourceInfo(Resource resource) {
		if(resource == null) return;
		
		boolean isExists = resource.exists();
		logger.info(">>" + isExists);
		
		if(isExists) {
			try {
				logger.info("isFile=" + resource.isFile());
				logger.info("description=" + resource.getDescription());
				logger.info("fileName=" + resource.getFilename());
				logger.info("absolutePath=" + resource.getFile().getAbsolutePath());
				logger.info("URI=" + resource.getURI());
				logger.info("URL=" + resource.getURL());
				
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		
	}
}
