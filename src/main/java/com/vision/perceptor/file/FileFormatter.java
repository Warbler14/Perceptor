package com.vision.perceptor.file;

import java.io.File;

import org.springframework.util.StringUtils;

public class FileFormatter {

	public FileData getFileInfo(final String path) {
		if(StringUtils.isEmpty(path)) {
			return null;
		}
		
		File file = new File(path);
		if( ! file.exists()) {
			return null;
		}
		FileData fileData = new FileData(file);
		
		return fileData;
	}
}
