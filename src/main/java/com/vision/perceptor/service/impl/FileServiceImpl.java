package com.vision.perceptor.service.impl;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vision.perceptor.file.FileData;
import com.vision.perceptor.file.FileFormatter;
import com.vision.perceptor.file.ImgDownload;
import com.vision.perceptor.file.jstree.JstreeFormatter;
import com.vision.perceptor.file.jstree.JstreeNode;
import com.vision.perceptor.model.FileModel;
import com.vision.perceptor.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	static final Logger logger = LoggerFactory.getLogger(FileService.class);
	
	public static String systemSaveFileRoot = "C:\\test\\image";
	
	public List<JstreeNode> subDirList(String source) {
		return new JstreeFormatter().subDirList(source);
	}
	
	public FileData getFileInfo(final String path) {
		return new FileFormatter().getFileInfo(path);
	}

	@Override
	public boolean addTask(FileModel file) {
		
		file.setAction("addTask");
		logger.info(file.getUserId());
		logger.info(file.getPassword());
		logger.info(file.getTargetPath());
		logger.info(file.getTargetParentPath());
		
		return false;
	}

	@Override
	public ImgDownload.DownloadFile downloadFile(FileModel file) {
		ImgDownload imgDownload = new ImgDownload();
		ImgDownload.DownloadFile downloadFile = getSavePath(file);		
		imgDownload.download(downloadFile);
		
		return downloadFile;
	}
	
	@Override
	public ImgDownload.DownloadFile getSavePath(FileModel file) {
		ImgDownload imgDownload = new ImgDownload();
		ImgDownload.DownloadFile downloadFile = imgDownload.new DownloadFile();
		
		String urls = file.getUrls();
		logger.info(urls);
		
		String fileName = urls.substring(urls.lastIndexOf("/")+1, urls.length());
		
		StringBuffer savePath = new StringBuffer();
		if(StringUtils.isEmpty(file.getSystemSaveFileRoot())){
			savePath.append(systemSaveFileRoot);
		} else {
			savePath.append(file.getSystemSaveFileRoot());
		}		
		savePath.append(File.separator);
		
		String userId = file.getUserId();
		if(! StringUtils.isEmpty(userId) && !"null".equals(userId)) {
			savePath.append(file.getUserId()).append(File.separator);
		}
		
		String savePathParent = savePath.toString();
		savePath.append(fileName);	
		
		String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		
		downloadFile.setSavePathParent(savePathParent);
		downloadFile.setSavePath(savePath.toString());
		downloadFile.setFileName(fileName);
		downloadFile.setUrl(urls);
		downloadFile.setExtension(extension);
		
		logger.info(downloadFile.toString());
		
		return downloadFile;
	}
	
	@Override
	public String getSavePath(String systemSaveFileRoot, String userId, String targetFileName ) {
		StringBuffer savePath = new StringBuffer();
		if(StringUtils.isEmpty(systemSaveFileRoot)){
			savePath.append(FileServiceImpl.systemSaveFileRoot);
		} else {
			savePath.append(systemSaveFileRoot);
		}		
		savePath.append(File.separator);
		
		if(! StringUtils.isEmpty(userId) && !"null".equals(userId)) {
			savePath.append(userId).append(File.separator);
		}
		
		savePath.append(targetFileName);	
		return savePath.toString();
	}
}
