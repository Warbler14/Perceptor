package com.vision.perceptor.dao;

import com.vision.perceptor.model.TestModel;

public interface TestDao {

	String getNow() throws Exception;
	
	TestModel getTest();
}
