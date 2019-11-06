package com.dream.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PersonalNote {
	
	@Id
    @GeneratedValue
    @Column(name = "Note_Id", nullable = false)
    private Long noteId;
	
	@Column(name = "Text", length = 1024, nullable = false)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "App_User_ID")
	private AppUser appUser;

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}	

}
