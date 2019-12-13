package com.vision.perceptor.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.perceptor.convert.Converter;
import com.vision.perceptor.file.FileData;
import com.vision.perceptor.file.ImgDownload;
import com.vision.perceptor.file.jstree.JstreeNode;
import com.vision.perceptor.image.CalcHist;
import com.vision.perceptor.image.CompareImages;
import com.vision.perceptor.image.CompareResult;
import com.vision.perceptor.model.AjaxModel;
import com.vision.perceptor.model.CompareFileModel;
import com.vision.perceptor.model.FileModel;
import com.vision.perceptor.model.TestModel;
import com.vision.perceptor.response.Result;
import com.vision.perceptor.service.FileService;
import com.vision.perceptor.service.ImageService;
import com.vision.perceptor.service.TestService;

@Controller
public class TestController {
	
	static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	FileService fileService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	ImageService imageService;

	@RequestMapping("/testGrid1")
	public String testGrid1() {
		return "test/grid1";
	}
	
	@RequestMapping("/testGrid2")
	public String testGrid2() {
		return "test/grid2";
	}
	
	@RequestMapping("/testGrid3")
	public String testGrid3() {
		return "test/grid3";
	}
	
	@RequestMapping("/testPage1")
	public String testPage1() {
		return "test/page1";
	}
	
	@RequestMapping("/testPage2")
	public String testPage2() {
		return "test/page2";
	}
	
	@RequestMapping("/testPage3")
	public String testPage3() {
		return "test/page3";
	}
	
	@RequestMapping("/testPage4")
	public String testPage4() {
		return "test/page4";
	}
	
	@RequestMapping("/testPage5")
	public String testPage5() {
		return "test/page5";
	}
	
	@RequestMapping("/testPage6")
	public String testPage6() {
		//download test
		return "test/page6";
	}
	
	@RequestMapping("/testImgPage1")
	public String testImgPage1() {
		return "test/imgPage1";
	}
	
	@RequestMapping("/testImgPage2")
	public String testImgPage2() {
		return "test/imgPage2";
	}
	
	@RequestMapping(value="/ajaxTest1", method=RequestMethod.POST)
	public @ResponseBody Result<AjaxModel> ajaxTest1(@RequestBody AjaxModel ajax) {
		
		logger.debug("~~~~~~~" + ajax.getTimezone() + ", " + ajax.getFormat());
		ajax.setTime(new Date().toString());
		
		return new Result<AjaxModel>(ajax);
	}
	
	@RequestMapping(value="/ajaxTest2", method=RequestMethod.POST)
	public @ResponseBody Result<AjaxModel> ajaxTest2(@RequestBody AjaxModel ajax) {
		
		logger.debug("ajaxTest");
		
		if(ajax == null) {
			return new Result<AjaxModel>(ajax);
		}
		
		String timeZone = ajax.getTimezone();
		String format = ajax.getFormat();
		
		Locale locale;
		String pattern;
		
		switch(timeZone) {
		case "Asia/Seoul" : 
			locale = Locale.KOREA;
			break;
		case "America/New_York":
			locale = Locale.US;
			break;
		default :
			locale = Locale.KOREA;
			break;
		}
		
		switch(format) {
		case "Y-m-d H:i:s" : 
			pattern = "yyyy-MM-dd HH:mm:ss";
			break;
		case "Y-m-d":
			pattern = "yyyy-MM-dd";
			break;
		default :
			pattern = "yyyy-MM-dd";
			break;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
		Date date = new Date();
				
		ajax.setTime(dateFormat.format(date));
		
		return new Result<AjaxModel>(ajax);
	}
	
	@RequestMapping(value="/ajaxTest3", method=RequestMethod.POST)
	public @ResponseBody Result<AjaxModel> ajaxTest4(@RequestBody AjaxModel ajax) {
		
		logger.debug(ajax.toString());
		ajax.setTime(new Date().toString());
		
		return new Result<AjaxModel>(ajax);
	}

	@RequestMapping(value="/ajaxFindFolder", method=RequestMethod.POST)
	public @ResponseBody Result<List<JstreeNode>> ajaxFindFolder(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		
		file.setActiveTime(String.valueOf(new Date().getTime()));
		
		logger.debug(">>" + file.toString());
		
		List<JstreeNode> obj = fileService.subDirList(file.getTargetPath());
				
		return new Result<List<JstreeNode>>(obj);
	}
	
	@RequestMapping(value="/ajaxFindFile", method=RequestMethod.POST)
	public @ResponseBody Result<FileData> ajaxFindFile(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		
		logger.debug("ajaxFindFile >>" + file.toString());
		
		FileData fileData = fileService.getFileInfo(file.getTargetPath());
		
		if(fileData != null) {
			logger.info(fileData.toString());
		}
				
		return new Result<FileData>(fileData);
	}
	
	@RequestMapping(value="/ajaxAddTask", method=RequestMethod.POST)
	public @ResponseBody Result<FileData> ajaxAddTask(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		
		logger.debug("ajaxAddTask >>" + file.toString());
		
		fileService.addTask(file);
		
		return new Result<FileData>(null);
	}
	
	@RequestMapping(value="/ajaxDownloadFile", method=RequestMethod.POST)
	public @ResponseBody Result<FileData> ajaxDownloadFile(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		
		logger.debug("ajaxAddTask >>" + file.toString());
		
		fileService.downloadFile(file);
		
		return new Result<FileData>(null);
	}
	
	
	@RequestMapping("/getTest")
//	public @ResponseBody Result<TestModel> getTest(@RequestParam("path") String path) {
	public @ResponseBody Result<TestModel> getTest() {
		
//		logger.debug(path);
		
//		fileService.subDirList("C:\\Intel");
		
//		DataBaseTest dataBaseTest = new DataBaseTest();
//		dataBaseTest.connect();
//		TestModel test = new TestModel();
		
		TestModel test = testService.printTestData();
		
		try {
			logger.info(">>" + testService.getNow());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		logger.info( test.getTestName() );
		
		return new Result<TestModel>(test);
	}
	
	@RequestMapping(value="/ajaxImgHistView", method=RequestMethod.POST)
	public @ResponseBody Result<FileData> ajaxImgHistView(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		logger.debug("ajaxImgHistView >>" + file.toString());
		
		ImgDownload.DownloadFile downloadFile = fileService.downloadFile(file);
		
		String filePath = downloadFile.getSavePath();
		System.out.println(filePath);
		String histFilePath = new CalcHist().calcHist(filePath);
		
		FileData fileData = null;
		try {
			fileData = new FileData(new File(histFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Result<FileData>(fileData);
	}
	
	@RequestMapping(value="/ajaxCompareImgHistView", method=RequestMethod.POST)
	public @ResponseBody Result<FileData> ajaxCompareImgHistView(@RequestBody Map<String, Object> param) {
		FileModel file = Converter.getBean(param, FileModel.class);
		logger.debug("ajaxCompareImgHistView >>" + file.toString());
		
		String orgFileSavePath = fileService.getSavePath(null, file.getUserId(), file.getTargetPath());
		FileData fileData = null;
		
		try {
			ImgDownload.DownloadFile downloadFile = fileService.downloadFile(file);
			String filePath = downloadFile.getSavePath();
			System.out.println("orgFileSavePath : " + orgFileSavePath);
			System.out.println("filePath : " + filePath);
		
			String histFilePath = new CalcHist().compareHist(orgFileSavePath, filePath);
			if(!StringUtils.isEmpty(histFilePath)) {
				fileData = new FileData(new File(histFilePath));
				
				CompareResult retVal = new CompareImages().compareHistogram(orgFileSavePath, filePath);
				logger.info("calcCorelation " + retVal.calcCorelation());
				logger.info("calcChiSquare " + retVal.calcChiSquare());
				logger.info("calcIntersection " + retVal.calcIntersection());
				logger.info("calcBhattaccharyya " + retVal.calcBhattaccharyya());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Result<FileData>(fileData);
	}
	
	@RequestMapping(value = "/viewLocalImg", method = RequestMethod.GET)
	public void viewLocalImg(FileModel fileModel, HttpServletResponse response) {
		logger.debug(">viewLocalImg start");
		
		
		try {
			String filePath = fileService.getSavePath(fileModel).getSavePath();
			fileModel.setTargetPath(filePath);
			
			
			imageService.getLocalImg(fileModel, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug(">viewLocalImg end");
	}


}
