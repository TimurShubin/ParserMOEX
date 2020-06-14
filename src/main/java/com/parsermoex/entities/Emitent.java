package com.parsermoex.entities;

import javax.persistence.*;

@Entity
@Table(name = "emitent")
public class Emitent {

	public Emitent(String secId, String secName, String emitentTitle, String regNumber) {
		this.secId = secId;
		this.secName = secName;
		this.emitentTitle = emitentTitle;
		this.regNumber = regNumber;
	}
	
	public Emitent() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emitentId")
	private long emitentId;
	
	@Column(name = "secId")
	private String secId;
	
	@Column(name = "emitentTitle")
	private String emitentTitle;
	
	@Column(name = "regNumber")
	private String regNumber;

	@Column(name = "secName")
	private String secName;

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getEmitentTitle() {
		return emitentTitle;
	}

	public void setEmitentTitle(String emitentTitle) {
		this.emitentTitle = emitentTitle;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	@Override
	public String toString() {
		return "Emitent [emitentId=" + emitentId + ", secId=" + secId + ", emitentTitle=" + emitentTitle
				+ ", regNumber=" + regNumber + ", secName=" + secName + "]";
	}
	
}
