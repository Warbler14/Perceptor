package com.vision.perceptor.dao;

import java.util.List;

import com.vision.perceptor.model.TestModel;

public interface TestDao {

	String getNow() throws Exception;
	
	List<TestModel> getTest();
}
