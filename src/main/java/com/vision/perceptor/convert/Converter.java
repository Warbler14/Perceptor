package com.vision.perceptor.convert;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.google.gson.Gson;

public class Converter {

	public static <T> T getBean(final Map<String, Object> param, Class<T> classOfT) {
		JSONObject jsonObject = new JSONObject(param);
		String jsonString = jsonObject.toString();		
		Gson gson = new Gson();
		T bean = gson.fromJson(jsonString, classOfT);		
		return bean;
	}
}
