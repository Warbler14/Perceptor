package com.vision.perceptor.model;

import java.util.Map;

import com.vision.perceptor.convert.Converter;

public class CompareFileModel extends TaskModel{

	private String systemSaveFileRoot;
	
//	private String targetPath;
//	
//	private String targetParentPath;
	
	private String urls1;
	
	private String urls2;
	
	public FileModel getFile(Map<String, Object> param, String urls) {
		FileModel file = Converter.getBean(param, FileModel.class);
		file.setUrls(urls);
		return file;
	}
	
	/**
	 * @return the systemSaveFileRoot
	 */
	public String getSystemSaveFileRoot() {
		return systemSaveFileRoot;
	}

	/**
	 * @param systemSaveFileRoot the systemSaveFileRoot to set
	 */
	public void setSystemSaveFileRoot(String systemSaveFileRoot) {
		this.systemSaveFileRoot = systemSaveFileRoot;
	}

//	/**
//	 * @return the targetPath
//	 */
//	public String getTargetPath() {
//		return targetPath;
//	}
//
//	/**
//	 * @param targetPath the targetPath to set
//	 */
//	public void setTargetPath(String targetPath) {
//		this.targetPath = targetPath;
//	}
//
//	/**
//	 * @return the targetParentPath
//	 */
//	public String getTargetParentPath() {
//		return targetParentPath;
//	}
//
//	/**
//	 * @param targetParentPath the targetParentPath to set
//	 */
//	public void setTargetParentPath(String targetParentPath) {
//		this.targetParentPath = targetParentPath;
//	}
//	
	/**
	 * @return the urls1
	 */
	public String getUrls1() {
		return urls1;
	}

	/**
	 * @param urls the urls1 to set
	 */
	public void setUrls1(String urls1) {
		this.urls1 = urls1;
	}
	
	/**
	 * @return the urls2
	 */
	public String getUrls2() {
		return urls2;
	}

	/**
	 * @param urls the urls2 to set
	 */
	public void setUrls2(String urls2) {
		this.urls2 = urls2;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileModel [");
		builder.append(super.toString());
		if (systemSaveFileRoot != null) {
			builder.append("systemSaveFileRoot=");
			builder.append(systemSaveFileRoot);
			builder.append(", ");
		}
//		if (targetPath != null) {
//			builder.append("targetPath=");
//			builder.append(targetPath);
//			builder.append(", ");
//		}
//		if (targetParentPath != null) {
//			builder.append("targetParentPath=");
//			builder.append(targetParentPath);
//			builder.append(", ");
//		}
		if (urls1 != null) {
			builder.append("urls1=");
			builder.append(urls1);
		}
		if (urls2 != null) {
			builder.append("urls2=");
			builder.append(urls2);
		}
		builder.append("]");
		return builder.toString();
	}

}
