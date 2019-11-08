package com.dream.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DreamPost {
	
	  @Id
	  @GeneratedValue
	  @Column(name = "Post_Id", nullable = false)
	  private Long postId;
	  
	  @Column(name = "Title", length = 128, nullable = false)
	  private String title;
	  
	  @Lob
	  @Column(name = "Description", nullable = false)
	  private String description;
	  
	  @ManyToOne
	  @JoinColumn(name = "App_User_ID")
	  private AppUser appUser;
	  
	  @Column(name = "Created_Date", length = 36, nullable = false)
	  private Date createdDate;
	  
	  @Column(name = "Modified_Date", length = 36, nullable = false)
	  private Date modifiedDate;
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(name = "Dream_Post_Keyword", joinColumns = { @JoinColumn(name = "Dream_Post_Id") }, 
	  inverseJoinColumns = {@JoinColumn(name = "Keyword_Id") })
	  private Set<Keyword> keywords = new HashSet<>();

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	 
}
