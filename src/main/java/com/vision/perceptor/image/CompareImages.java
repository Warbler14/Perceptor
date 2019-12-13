package com.vision.perceptor.image;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FastFeatureDetector;
import org.opencv.features2d.Feature2D;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CompareImages {

	public void compareImages(String filename1, String filename2) {
		int retVal = 0;
		long startTime = System.currentTimeMillis();

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Load images to compare
		Mat img1 = Imgcodecs.imread(filename1, Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = Imgcodecs.imread(filename2, Imgcodecs.CV_LOAD_IMAGE_COLOR);

		// Declare key point of images
		MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
		MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
		Mat descriptors1 = new Mat();
		Mat descriptors2 = new Mat();

		// Definition of ORB key point detector and descriptor extractors
		FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
		DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

		// Detect key points
		detector.detect(img1, keypoints1);
		detector.detect(img2, keypoints2);

		// Extract descriptors
		extractor.compute(img1, keypoints1, descriptors1);
		extractor.compute(img2, keypoints2, descriptors2);

		// Definition of descriptor matcher
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

		// Match points of two images
		MatOfDMatch matches = new MatOfDMatch();
		// System.out.println("Type of Image1= " + descriptors1.type() + ", Type of
		// Image2= " + descriptors2.type());
		// System.out.println("Cols of Image1= " + descriptors1.cols() + ", Cols of
		// Image2= " + descriptors2.cols());

		// Avoid to assertion failed
		// Assertion failed (type == src2.type() && src1.cols == src2.cols && (type ==
		// CV_32F || type == CV_8U)
		if (descriptors2.cols() == descriptors1.cols()) {
			matcher.match(descriptors1, descriptors2, matches);

			// Check matches of key points
			DMatch[] match = matches.toArray();
			double max_dist = 0;
			double min_dist = 100;

			for (int i = 0; i < descriptors1.rows(); i++) {
				double dist = match[i].distance;
				if (dist < min_dist)
					min_dist = dist;
				if (dist > max_dist)
					max_dist = dist;
			}
			System.out.println("max_dist=" + max_dist + ", min_dist=" + min_dist);

			// Extract good images (distances are under 10)
			for (int i = 0; i < descriptors1.rows(); i++) {
				if (match[i].distance <= 10) {
					retVal++;
				}
			}
			System.out.println("matching count=" + retVal);
		}

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("estimatedTime=" + estimatedTime + "ms");

	}
	
	public CompareResult compareHistogram(String filename1, String filename2) {
		
		long startTime = System.currentTimeMillis();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//Load image to compare
		Mat img1 = Imgcodecs.imread(filename1, Imgcodecs.CV_LOAD_IMAGE_COLOR);
		Mat img2 = Imgcodecs.imread(filename2, Imgcodecs.CV_LOAD_IMAGE_COLOR);
		
		Mat hsvImg1 = new Mat();
		Mat hsvImg2= new Mat();
		
		//convert to HSV
		Imgproc.cvtColor(img1, hsvImg1, Imgproc.COLOR_BGR2HSV);
		Imgproc.cvtColor(img2, hsvImg2, Imgproc.COLOR_BGR2HSV);
		
		//Set configuration for calchist()
		List<Mat> listImg1 = new ArrayList<Mat>();
		List<Mat> listImg2 = new ArrayList<Mat>();
		
		listImg1.add(hsvImg1);
		listImg2.add(hsvImg2);
		
		MatOfFloat ranges = new MatOfFloat(0,255);
		MatOfInt histSize = new MatOfInt(50);
		MatOfInt channels = new MatOfInt(0);
		
		//Histograms
		Mat histImg1 = new Mat();
		Mat histImg2 = new Mat();
		
		//Calculate the histogram for the HSV images
		Imgproc.calcHist(listImg1, channels, new Mat(), histImg1, histSize, ranges);
		Imgproc.calcHist(listImg2, channels, new Mat(), histImg2, histSize, ranges);
		
		Core.normalize(histImg1, histImg1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
		Core.normalize(histImg2, histImg2, 0, 1, Core.NORM_MINMAX, -1, new Mat());
		
		//Apply the histogram comparison methods
		// 0 - correlation : the higher the metric, the more accurate the match "> 0.9"
		// 1 - chi-square : the lower the metric, the more accurate the match "< 0.1"
		// 2 - intersection : the higher the metric, the more accurate the match "> 1.5"
		// 3 - bhattacharyya : the lower the metric, the more accurate the match "<0.3"
		
		double result0, result1, result2, result3;
		result0 = Imgproc.compareHist(histImg1, histImg2, 0);
		result1 = Imgproc.compareHist(histImg1, histImg2, 1);
		result2 = Imgproc.compareHist(histImg1, histImg2, 2);
		result3 = Imgproc.compareHist(histImg1, histImg2, 3);
		
		System.out.println("Method [0] " + result0);
		System.out.println("Method [1] " + result1);
		System.out.println("Method [2] " + result2);
		System.out.println("Method [3] " + result3);
		
		CompareResult compareResult = new CompareResult();
		compareResult.setCorelation(result0);
		compareResult.setChiSquare(result1);
		compareResult.setIntersection(result2);
		compareResult.setBhattaccharyya(result3);
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("estimatedTime=" + estimatedTime + "ms");
		
		return compareResult;
	}
}
