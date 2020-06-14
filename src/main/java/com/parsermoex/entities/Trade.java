package com.parsermoex.entities;

import javax.persistence.*;

@Entity
@Table(name = "trade")
public class Trade {

	public Trade(String secId, String tradeDate, Double numTrades, Double open, Double close) {
		this.secId = secId;
		this.tradeDate = tradeDate;
		this.numTrades = numTrades;
		this.open = open;
		this.close = close;
	}
	
	public Trade() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tradeId")
	private long tradeId;
		
	@Column(name = "secId")
	private String secId;
	
	@Column(name = "tradeDate")
	private String tradeDate;
	
	@Column(name = "numTrades")
	private Double numTrades;
	
	@Column(name = "openPrice")
	private Double open;
	
	@Column(name = "closePrice")
	private Double close;

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
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

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

}
