package com.vision.perceptor.model;

public class AjaxModel {

	private String timezone;
	private String format;
	private String time;
	private String targetPath;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AjaxModel [");
		if (timezone != null) {
			builder.append("timezone=");
			builder.append(timezone);
			builder.append(", ");
		}
		if (format != null) {
			builder.append("format=");
			builder.append(format);
			builder.append(", ");
		}
		if (time != null) {
			builder.append("time=");
			builder.append(time);
			builder.append(", ");
		}
		if (targetPath != null) {
			builder.append("targetPath=");
			builder.append(targetPath);
		}
		builder.append("]");
		return builder.toString();
	}

	
	
}
