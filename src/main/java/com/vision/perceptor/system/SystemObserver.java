package com.vision.perceptor.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemObserver {
	
	static final Logger logger = LoggerFactory.getLogger(SystemObserver.class);
	
	private ExecutorService executorService;
	private List<Runnable> runList = new ArrayList<Runnable>();
	private AtomicBoolean isStart = new AtomicBoolean(false);
	
	
	public void setObserveObjects(Object... subjects) {
		//시작 상태이면 조치
		if( isStart.get() ) {
			logger.info("SystemObserver already started");
			return;
		}
		
		for(Object subject : subjects) {
			if( subject instanceof String) {
				logger.info(String.valueOf(subject));
			} else if(subject instanceof Runnable) {
				runList.add((Runnable)subject);
			}
		}
	}
	
	public void addObserveObjects(Object subject) {
		if( isStart.get() ) {
			logger.info("SystemObserver already started");
			return;
		}
		
		if( subject instanceof String) {
			logger.info(String.valueOf(subject));
		} else if(subject instanceof Runnable) {
			runList.add((Runnable)subject);
		}
	}
	
	public void startObserveObjects() {
		executorService = Executors.newFixedThreadPool(runList.size());
		for (int idx = 0; idx < runList.size(); idx++) {
			Runnable runableObject = runList.get(idx); 
			logger.info(runableObject.getClass().getName() + " start");
			executorService.execute(runableObject);
		}
		
		logger.info("Start observe");
		isStart.set(true);
	}
	
	public void getObserveObject() {
//		executorService.
	}
	
	public void shutdown() {
		logger.info("Stop system observer");
		if(executorService != null) {
			isStart.set(false);
			executorService.shutdown();
			try {
				if(executorService != null) {
					executorService = null;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
