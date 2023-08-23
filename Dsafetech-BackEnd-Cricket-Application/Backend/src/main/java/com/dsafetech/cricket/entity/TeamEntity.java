package com.dsafetech.cricket.entity;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
@Document(collection = "teamEntity")
public class TeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String teamName;

	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCaptainName() {
		return captainName;
	}
	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(long contactnumber) {
		this.contactnumber = contactnumber;
	}
	public List<Teamplayers> getTeamplayers() {
		return teamplayers;
	}
	public void setTeamplayers(List<Teamplayers> teamplayers) {
		this.teamplayers = teamplayers;
	}
	public TeamEntity(String teamName, String captainName, String coachName, String emailId, String date,
			long contactnumber, List<Teamplayers> teamplayers) {
		super();
		this.teamName = teamName;
		this.captainName = captainName;
		this.coachName = coachName;
		this.emailId = emailId;
		this.date = date;
		this.contactnumber = contactnumber;
		this.teamplayers = teamplayers;
	}
	public TeamEntity() {
		super();
	}
	private String captainName;
	private String   coachName;
	private String emailId;
	private String date;
	private long contactnumber;
	private List<Teamplayers> teamplayers;
	
	
}