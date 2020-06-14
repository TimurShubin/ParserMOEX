package com.parsermoex.entities;

import javax.persistence.*;

@Entity
public class TradeString {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String secId;
	
	private Long tradeId;
	private String tradeDate;
	private Double numTrades;
	private Double openPrice;
	private Double closePrice;
	private String secName;
	private String emitentTitle;
	private String regNumber;
	
	public String getSecId() {
		return secId;
	}
	public void setSecId(String secId) {
		this.secId = secId;
	}

	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public Double getNumTrades() {
		return numTrades;
	}
	public void setNumTrades(Double numTrades) {
		this.numTrades = numTrades;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
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
	
	
	
}
