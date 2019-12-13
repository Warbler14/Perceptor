package com.vision.perceptor.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vision.perceptor.task.ImageHistogramTestWork;
import com.vision.perceptor.task.WatcherWork;

@Configuration
public class SystemBeans {
	
	@Bean
	public WatcherWork getObserver() {
		return new WatcherWork();
	}
	
	@Bean
	public ImageHistogramTestWork getImageHistogramTestWork() {
		return new ImageHistogramTestWork();
	}
	
}
