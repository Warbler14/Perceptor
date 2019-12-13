package com.vision.perceptor.model;

public class FileModel extends TaskModel{

	private String systemSaveFileRoot;
	
	private String targetPath;
	
	private String targetParentPath;
	
	private String urls;
	
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

	/**
	 * @return the targetPath
	 */
	public String getTargetPath() {
		return targetPath;
	}

	/**
	 * @param targetPath the targetPath to set
	 */
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * @return the targetParentPath
	 */
	public String getTargetParentPath() {
		return targetParentPath;
	}

	/**
	 * @param targetParentPath the targetParentPath to set
	 */
	public void setTargetParentPath(String targetParentPath) {
		this.targetParentPath = targetParentPath;
	}
	
	/**
	 * @return the urls
	 */
	public String getUrls() {
		return urls;
	}

	/**
	 * @param urls the urls to set
	 */
	public void setUrls(String urls) {
		this.urls = urls;
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
		if (targetPath != null) {
			builder.append("targetPath=");
			builder.append(targetPath);
			builder.append(", ");
		}
		if (targetParentPath != null) {
			builder.append("targetParentPath=");
			builder.append(targetParentPath);
			builder.append(", ");
		}
		if (urls != null) {
			builder.append("urls=");
			builder.append(urls);
		}
		builder.append("]");
		return builder.toString();
	}

	

	

	
	
}
