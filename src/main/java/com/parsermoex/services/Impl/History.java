package com.parsermoex.services.Impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.TradeString;
import com.parsermoex.repositories.TradeRepository;
import com.parsermoex.services.IService;

@Service
public class History implements IService {

	@Autowired
	private TradeRepository tradeRepository;
	
	@Override
	public List<TradeString> getHistory() {
		return tradeRepository.getHistory();
	}

	@Override
	public void updateTrade(String tradeDate, Double openPrice, Double closePrice, Double numTrades, long id) {
		tradeRepository.updateTrade(tradeDate, openPrice, closePrice, numTrades, id);
	}
	
	@Override
	public void updateEmitent(String secName, String emitentTitle, String regNumber, String secId) {
		tradeRepository.updateEmitent(secName, emitentTitle, regNumber, secId);
	}
	
	@Override
	public void removeTrade(long id) {
		tradeRepository.removeTrade(id);
	}

	@Override
	public List<Emitent> getMissingEmitents() {
		return tradeRepository.getMissingEmitent();
	}
	
}
