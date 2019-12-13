package com.vision.perceptor.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.vision.perceptor.image.CompareImages;

@Service
public class SystemManager implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {

	static final Logger logger = LoggerFactory.getLogger(SystemManager.class);

	public static final String STATIC_RESOURCES_PATH = "classpath:static";
	
	@Autowired
	SystemObserver systemObserver;
	
	@Autowired
	SystemBeans systemBeans;
		
	@Override
	public void run(String... args) throws Exception {
		logger.info("SystemManager start ");
				
		systemObserver.setObserveObjects(systemBeans.getObserver());
		systemObserver.addObserveObjects(systemBeans.getImageHistogramTestWork());
		systemObserver.startObserveObjects();
	}
	
	@Override
	public void onApplicationEvent(ContextClosedEvent arg0) {
		logger.info("SystemManager stopping ");
		
		
		systemObserver.shutdown();
		
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("System start ");
		
	}

	@Override
	public void destroy() throws Exception {
		logger.info("System down");
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
				// TODO: handle exception
			}
		}
		
	}


}
