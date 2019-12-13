package com.vision.perceptor.image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CalcHist {
	
	public String calcHist(String filename) {
		String histFilePath = null;
		
		try {
			Mat src = Imgcodecs.imread(filename);
			if (src.empty()) {
				System.err.println("Cannot read image: " + filename);
				System.exit(0);
			}
			
			int histSize = 256;
			float[] range = { 0, 256 }; // the upper boundary is exclusive
			boolean accumulate = false;
			int histW = 512, histH = 400;
			Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
			
			MatGroup matGroup = new MatGroup();
			calcHist(histSize, src, matGroup, range, accumulate);
			normalize(histSize, matGroup, histImage, histW, histH);
			drawsHistLines(histSize, matGroup, histImage, histW, histH, 1);
			
			histFilePath = getModifiyFileName(filename, "hist");
			Imgcodecs.imwrite(histFilePath, histImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return histFilePath;
	}
	
	public String compareHist(String filename1, String filename2) {
		String histFilePath = null;
		
		if(!isExist(filename1)) return null;
		if(!isExist(filename2)) return null;
		
		try {
			Mat src1 = Imgcodecs.imread(filename1);
			if (src1.empty()) {
				System.err.println("Cannot read image: " + filename1);
				System.exit(0);
			}
			Mat src2 = Imgcodecs.imread(filename2);
			if (src2.empty()) {
				System.err.println("Cannot read image: " + filename2);
				System.exit(0);
			}
			
			int histSize = 256;
			float[] range = { 0, 256 }; // the upper boundary is exclusive
			boolean accumulate = false;
			int histW = 512, histH = 400;
			Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
			
			MatGroup matGroup1 = new MatGroup();
			MatGroup matGroup2 = new MatGroup();
			
			calcHist(histSize, src1, matGroup1, range, accumulate);
			normalize(histSize, matGroup1, histImage, histW, histH);
			drawsHistLines(histSize, matGroup1, histImage, histW, histH, 1);
			
			calcHist(histSize, src2, matGroup2, range, accumulate);
			normalize(histSize, matGroup2, histImage, histW, histH);
			drawsHistLines(histSize, matGroup2, histImage, histW, histH, 2);
			
			histFilePath = getModifiyFileName(filename2, "hist");
			Imgcodecs.imwrite(histFilePath, histImage);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return histFilePath;
	}
	
	public boolean isExist(String path) {
		try {
			File file = new File(path);
			if(file.exists() && file.isFile()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public void calcHist(int histSize, Mat src, MatGroup matGroup, float[] range, boolean accumulate) {
		List<Mat> bgrPlanes = new ArrayList<>();
		Core.split(src, bgrPlanes);
		MatOfFloat histRange = new MatOfFloat(range);
		Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), matGroup.getbHist(), new MatOfInt(histSize), histRange, accumulate);
		Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), matGroup.getgHist(), new MatOfInt(histSize), histRange, accumulate);
		Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), matGroup.getrHist(), new MatOfInt(histSize), histRange, accumulate);
	}
	
	public void normalize(int histSize, MatGroup matGroup, Mat histImage, int histW, int  histH) {
		Mat bHist = matGroup.getbHist();
		Mat gHist = matGroup.getgHist();
		Mat rHist = matGroup.getrHist();
		
		Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
		Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
		Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
		float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
		bHist.get(0, 0, bHistData);
		float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
		gHist.get(0, 0, gHistData);
		float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
		rHist.get(0, 0, rHistData);
		
		matGroup.setbHistData(bHistData);
		matGroup.setgHistData(gHistData);
		matGroup.setrHistData(rHistData);
	}
	
	public void drawsHistLines(int histSize, MatGroup matGroup, Mat histImage, int histW, int histH, int thickness) {
		float[] bHistData = matGroup.getbHistData();
		float[] gHistData = matGroup.getgHistData();
		float[] rHistData = matGroup.getrHistData();
		
		int binW = (int) Math.round((double) histW / histSize);
		
		for (int i = 1; i < histSize; i++) {
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 0, 0), thickness);
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(gHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(gHistData[i])), new Scalar(0, 255, 0), thickness);
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(rHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(rHistData[i])), new Scalar(0, 0, 255), thickness);
		}
	}
	
	public static class MatGroup{
		private Mat bHist = new Mat();
		private Mat gHist = new Mat();
		private Mat rHist = new Mat();
		
		private float[] bHistData;
		private float[] gHistData;
		private float[] rHistData;
		
		public Mat getbHist() {
			return bHist;
		}
		public void setbHist(Mat bHist) {
			this.bHist = bHist;
		}
		public Mat getgHist() {
			return gHist;
		}
		public void setgHist(Mat gHist) {
			this.gHist = gHist;
		}
		public Mat getrHist() {
			return rHist;
		}
		public void setrHist(Mat rHist) {
			this.rHist = rHist;
		}
		public float[] getbHistData() {
			return bHistData;
		}
		public void setbHistData(float[] bHistData) {
			this.bHistData = bHistData;
		}
		public float[] getgHistData() {
			return gHistData;
		}
		public void setgHistData(float[] gHistData) {
			this.gHistData = gHistData;
		}
		public float[] getrHistData() {
			return rHistData;
		}
		public void setrHistData(float[] rHistData) {
			this.rHistData = rHistData;
		}
	}
	
	private static String getModifiyFileName(final String filePath, final String addName) {
		Integer extendIndex = filePath.lastIndexOf(".");
		StringBuilder builder = new StringBuilder( filePath.substring(0, extendIndex));
		builder.append("-").append(addName).append(".png");
		return builder.toString();
	}
	
	public void run(String filename) {
		Mat src = Imgcodecs.imread(filename);
		if (src.empty()) {
			System.err.println("Cannot read image: " + filename);
			System.exit(0);
		}
		List<Mat> bgrPlanes = new ArrayList<>();
		Core.split(src, bgrPlanes);
		int histSize = 256;
		float[] range = { 0, 256 }; // the upper boundary is exclusive
		MatOfFloat histRange = new MatOfFloat(range);
		boolean accumulate = false;
		Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
		Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), histRange, accumulate);
		Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), histRange, accumulate);
		Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), histRange, accumulate);
		
		
		int histW = 512, histH = 400;
		int binW = (int) Math.round((double) histW / histSize);
		Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
		Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
		Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
		Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
		float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
		bHist.get(0, 0, bHistData);
		float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
		gHist.get(0, 0, gHistData);
		float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
		rHist.get(0, 0, rHistData);
		
		
		for (int i = 1; i < histSize; i++) {
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 0, 0), 1);
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(gHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(gHistData[i])), new Scalar(0, 255, 0), 1);
			Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(rHistData[i - 1])),
					new Point(binW * (i), histH - Math.round(rHistData[i])), new Scalar(0, 0, 255), 1);
		}
		
		Imgcodecs.imwrite(getModifiyFileName(filename, "hist"), histImage);
		
		HighGui.imshow("Source image", src);
		HighGui.imshow("calcHist Demo", histImage);
		HighGui.waitKey(0);
		System.exit(0);
	}
}
