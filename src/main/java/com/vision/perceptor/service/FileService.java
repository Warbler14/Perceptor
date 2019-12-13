package com.vision.perceptor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vision.perceptor.file.FileData;
import com.vision.perceptor.file.ImgDownload;
import com.vision.perceptor.file.jstree.JstreeNode;
import com.vision.perceptor.model.FileModel;

@Service
public interface FileService {

	public List<JstreeNode> subDirList(String source);
	
	public FileData getFileInfo(final String path);
	
	public boolean addTask(FileModel file);
	
	public ImgDownload.DownloadFile downloadFile(FileModel file);
	
	public ImgDownload.DownloadFile getSavePath(FileModel file);
	
	public String getSavePath(String systemSaveFileRoot, String userId, String targetFileName);
}
