package com.vision.perceptor.file.jstree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JstreeFormatter implements JstreeDescription{

	static final Logger logger = LoggerFactory.getLogger(JstreeFormatter.class);
	
	public List<JstreeNode> subDirList(String source) {
		List<JstreeNode> jstreeFileList = new ArrayList<JstreeNode>();

		File dir = new File(source);
		if( ! dir.exists() ) {
			return null;
		}
		
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				JstreeNode jstreeNode = new JstreeNode(file);
				String name = file.getName();

				if (file.isDirectory()) {
					if(logger.isDebugEnabled()) {
						logger.debug("directory name = " + name + " " + file.getCanonicalPath());
					}
					List<JstreeNode> children = subDirList(file.getCanonicalPath());
					jstreeNode.setChildren(children);
				}
				
				if(logger.isDebugEnabled()) {
					if (file.isFile()) {
						logger.debug("file name = " + name);
					}
				}
				
				jstreeFileList.add(jstreeNode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jstreeFileList;
	}
}
