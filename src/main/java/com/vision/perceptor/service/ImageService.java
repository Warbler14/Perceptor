package com.vision.perceptor.service;

import javax.servlet.http.HttpServletResponse;

import com.vision.perceptor.model.FileModel;


public interface ImageService {

	void getLocalImg(FileModel fileModel, HttpServletResponse response);
}
