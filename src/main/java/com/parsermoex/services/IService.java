package com.parsermoex.services;

import java.util.List;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.TradeString;

public interface IService {

	public List<TradeString> getHistory();
	public void removeTrade(long id);
	public void updateTrade(String tradeDate, Double openPrice, Double closePrice, String numTrades, long id);
	public List<Emitent> getMissingEmitents();
	
}
