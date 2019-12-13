package com.vision.perceptor.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vision.perceptor.file.FileData;
import com.vision.perceptor.model.FileModel;
import com.vision.perceptor.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

	public static String systemSaveFileRoot = "C:\\test\\image";
	
	@Override
	public void getLocalImg(FileModel fileModel, HttpServletResponse response) {
		try {
	
			String canonicalPath = fileModel.getTargetPath();
			
			logger.info(canonicalPath);
			File f = new File(canonicalPath);
			
			if(f.exists() && f.isFile()){
				String extention 
					= canonicalPath.substring(canonicalPath.lastIndexOf(".") + 1
												, canonicalPath.length());
				
				BufferedImage bi = ImageIO.read(f);
				Graphics2D g = (Graphics2D) bi.getGraphics();
				
				if("jpg".equals(extention) || "jpeg".equals(extention)) {
					response.setContentType("image/jpeg");
					g.setPaint(Color.white);
					OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "jpg", out);
					
				} else if("png".equals(extention)) {
					response.setContentType("image/png");
					OutputStream out = response.getOutputStream();
					ImageIO.write(bi, "png", out);
				}
			}else{
				response.getWriter().println("<h1>There is no image</h1>");
				return;
			}
			

		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage());
		}
		
	}
	
	
	


}
