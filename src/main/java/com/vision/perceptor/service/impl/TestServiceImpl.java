package com.vision.perceptor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vision.perceptor.dao.TestDao;
import com.vision.perceptor.model.TestModel;
import com.vision.perceptor.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;

	@Override
	public String getNow() throws Exception{
		return testDao.getNow();
	}
	
	@Override
	public TestModel printTestData() {
		TestModel test = testDao.getTest();
		if(test == null) {
			System.out.println( "null" );
		}
		return test;
	}



}
