package com.vision.perceptor.service;

import java.util.List;

import com.vision.perceptor.model.TestModel;

public interface TestService {

	String getNow() throws Exception;
	
	List<TestModel> printTestData();
}
