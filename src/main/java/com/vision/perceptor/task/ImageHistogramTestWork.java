package com.vision.perceptor.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.vision.perceptor.image.CompareImages;
import com.vision.perceptor.system.SystemResources;

public class ImageHistogramTestWork implements Work{

	@Autowired
	SystemResources systemResource;
	
	public boolean execute = false;
	
	@Override
	public void run() {
		initalize();
		execute = true;
		
		histogramTest();
		
		execute = false;
	}

	@Override
	public void initalize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		execute = false;
		
	}
	
	public void histogramTest() {
		String jpg1Path = systemResource.getResourceFilePath("/images/test01.jpg");
		String jpg2Path = systemResource.getResourceFilePath("/images/test02.jpg");
		String jpg1DamagedPath = systemResource.getResourceFilePath("/images/test01-damaged.jpg");
		
		String png1Path = systemResource.getResourceFilePath("/images/test01.png");
		String png2Path = systemResource.getResourceFilePath("/images/test02.png");
		String png1DamagedPath = systemResource.getResourceFilePath("/images/test01-damaged.png");
		
		System.out.println("=========Maching histogram test jpg=========");
		new CompareImages().compareHistogram(jpg1Path, jpg1Path);
		new CompareImages().compareHistogram(jpg1Path, jpg2Path);
		new CompareImages().compareHistogram(jpg1Path, jpg1DamagedPath);
		
		System.out.println("=========Maching histogram test png=========");
		new CompareImages().compareHistogram(png1Path, png1Path);
		new CompareImages().compareHistogram(png1Path, png2Path);
		new CompareImages().compareHistogram(png1Path, png1DamagedPath);
	}

}
