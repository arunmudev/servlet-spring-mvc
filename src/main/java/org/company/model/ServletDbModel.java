package org.company.model;

import java.time.LocalDateTime;

public class ServletDbModel {

	private Integer issueId;
	private String issueTitle;
	private String assignee;
	private String priority;
	private boolean status;
	private String issueStatus;
	private String lastUpdatedTime;

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Constructor
	 * @param title
	 */
	public ServletDbModel(Integer issueId,String issueTitle,String assignee,String priority,String issueStatus,String lastUpdatedTime) {
		this.issueId = issueId;
		this.issueTitle = issueTitle;
		this.assignee = assignee;
		this.priority=priority;
		this.issueStatus = issueStatus;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public ServletDbModel(boolean status) {
		this.status = status;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	public ServletDbModel(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ServletDbModel() {
		// TODO Auto-generated constructor stub
	}

	public void setIssueId(Integer issueId){
		this.issueId = issueId;
	}

	public Integer getIssueId(){
		return issueId;
	}

	public void setIssueTitle(String issueTitle){
		this.issueTitle = issueTitle;
	}

	public String getIssueTitle(){
		return issueTitle;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}
	

}