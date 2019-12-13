package com.vision.perceptor.file;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class FileData implements FileDescription{

	private String name;

	private String canonicalPath;

	private String parent;

	private String type;
	
	private long lastModified;
	
	private Date lastModifiedDate;
	
	private DateInfo lastModifiedDateInfo;
	
	private long length;
	
	private boolean canRead;
	
	private boolean canWrite;
	
	public FileData() {
		super();
	}

	public FileData(File file) {
		this();

		if (file == null || ! file.exists()) {
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
		
		lastModified = file.lastModified();		
		if(lastModified > 0 ) {
			lastModifiedDate = new Date(lastModified);
		}		
		lastModifiedDateInfo = new DateInfo(lastModifiedDate);
		
		length = file.length();
		canRead = file.canRead();
		canWrite = file.canWrite();
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
	
	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public DateInfo getLastModifiedDateInfo() {
		return lastModifiedDateInfo;
	}

	public void setLastModifiedDateInfo(DateInfo lastModifiedDateInfo) {
		this.lastModifiedDateInfo = lastModifiedDateInfo;
	}

	public boolean isCanRead() {
		return canRead;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileData [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (canonicalPath != null) {
			builder.append("canonicalPath=");
			builder.append(canonicalPath);
			builder.append(", ");
		}
		if (parent != null) {
			builder.append("parent=");
			builder.append(parent);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		builder.append("]");
		return builder.toString();
	}
	
	public static class DateInfo{
	
		private int year;
		
		private int month;
		
		private int day;
		
		private int hour;
		
		private int minute;
		
		private int second;
		
		private int dayOfWeek;
		
		public DateInfo(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.DAY_OF_MONTH) + 1;
			day = cal.get(Calendar.DAY_OF_MONTH);
			hour = cal.get(Calendar.HOUR_OF_DAY);
			minute = cal.get(Calendar.MINUTE);
			second = cal.get(Calendar.SECOND);
			
			dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public int getMinute() {
			return minute;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public int getDayOfWeek() {
			return dayOfWeek;
		}

		public void setDayOfWeek(int dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}
		
	}
	
}
