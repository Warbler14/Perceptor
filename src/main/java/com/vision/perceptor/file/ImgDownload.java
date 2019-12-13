package com.vision.perceptor.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgDownload {

	public boolean download(DownloadFile downloadFile) {
		if(downloadFile == null) {
			return false;
		}
		
		boolean status = true;
		
		try {
			URL url = new URL(downloadFile.getUrl());
			BufferedImage img = ImageIO.read(url);
			
			File dirFile = new File(downloadFile.getSavePathParent());
			if(!dirFile.exists()) {
				dirFile.mkdir();
			}
			
			File file = new File(downloadFile.getSavePath());
			ImageIO.write(img, downloadFile.getExtension(), file);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		
		return status;
		
	}
	
	public class DownloadFile{
		private String savePath;
		private String savePathParent;
		private String url;
		private String fileName;
		private String extension;
		
		/**
		 * @return the savePath
		 */
		public String getSavePath() {
			return savePath;
		}
		/**
		 * @param savePath the savePath to set
		 */
		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}		
		/**
		 * @return the savePathParent
		 */
		public String getSavePathParent() {
			return savePathParent;
		}
		/**
		 * @param savePathParent the savePathParent to set
		 */
		public void setSavePathParent(String savePathParent) {
			this.savePathParent = savePathParent;
		}
		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}
		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		/**
		 * @return the extension
		 */
		public String getExtension() {
			return extension;
		}
		/**
		 * @param extension the extension to set
		 */
		public void setExtension(String extension) {
			this.extension = extension;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("DownloadFile [");
			if (savePath != null) {
				builder.append("savePath=");
				builder.append(savePath);
				builder.append(", ");
			}
			if (savePathParent != null) {
				builder.append("savePathParent=");
				builder.append(savePathParent);
				builder.append(", ");
			}
			if (url != null) {
				builder.append("url=");
				builder.append(url);
				builder.append(", ");
			}
			if (fileName != null) {
				builder.append("fileName=");
				builder.append(fileName);
				builder.append(", ");
			}
			if (extension != null) {
				builder.append("extension=");
				builder.append(extension);
			}
			builder.append("]");
			return builder.toString();
		}
		
		
	}
}
