package com.vision.perceptor.model;

public class TaskModel extends UserModel{

	private String action;
	
	private String activeTime;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaskModel [");
		builder.append(super.toString());
		if (action != null) {
			builder.append("action=");
			builder.append(action);
			builder.append(", ");
		}
		if (activeTime != null) {
			builder.append("activeTime=");
			builder.append(activeTime);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
