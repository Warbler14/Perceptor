package com.vision.perceptor.file.jstree;

import java.io.File;
import java.util.List;

public class JstreeNode implements JstreeDescription{

	private String text;

	private String icon;

	private JstreeFileData data;

	private List<JstreeNode> children;
	
	public JstreeNode(File file) {
		if (file == null) {
			return;
		}
		this.text = file.getName();
		this.data = new JstreeFileData(file);
		if (file.isFile()) {
			this.icon = FILE_ICON;
		} else if (file.isDirectory()) {
			this.icon = FOLDER_ICON;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public JstreeFileData getData() {
		return data;
	}

	public void setData(JstreeFileData data) {
		this.data = data;
	}

	public List<JstreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<JstreeNode> children) {
		this.children = children;
	}

}
