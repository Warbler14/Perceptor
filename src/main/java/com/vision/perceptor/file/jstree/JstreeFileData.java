package com.vision.perceptor.file.jstree;

import java.io.File;
import java.io.IOException;

public class JstreeFileData implements JstreeDescription {

	private String name;

	private String canonicalPath;

	private String parent;

	private String type;

	public JstreeFileData() {
		super();
	}

	public JstreeFileData(File file) {
		this();

		if (file == null) {
			return;
		}

		try {
			this.name = file.getName();
			this.canonicalPath = file.getCanonicalPath();
			this.parent = file.getParent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (file.isFile()) {
			this.type = FILE;
		} else if (file.isDirectory()) {
			this.type = FOLDER;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCanonicalPath() {
		return canonicalPath;
	}

	public void setCanonicalPath(String canonicalPath) {
		this.canonicalPath = canonicalPath;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
