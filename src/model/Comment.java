package model;

import java.util.Date;

public class Comment {
	private int cmtID;//pk
	private String content;
	private Date date;
	private int userID;//fk
	private int artwId;//fk
	
	public Comment(int cmtID, String content, Date date, int userID, int artwId) {
		super();
		this.cmtID = cmtID;
		this.content = content;
		this.date = date;
		this.userID = userID;
		this.artwId = artwId;
	}

	public int getCmtID() {
		return cmtID;
	}

	public void setCmtID(int cmtID) {
		this.cmtID = cmtID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getArtwId() {
		return artwId;
	}

	public void setArtwId(int artwId) {
		this.artwId = artwId;
	}
	
	
}
