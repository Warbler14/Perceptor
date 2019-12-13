package com.vision.perceptor.service;

import com.vision.perceptor.model.TestModel;

public interface TestService {

	String getNow() throws Exception;
	
	TestModel printTestData();
}
