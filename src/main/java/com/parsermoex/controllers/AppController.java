package com.parsermoex.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.Trade;
import com.parsermoex.entities.TradeString;
import com.parsermoex.services.Impl.History;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppController {

	@Autowired
	private History history;

	Set<Trade> trades = new HashSet<>();
	Set<Emitent> emitents = new HashSet<>();
	
	@RequestMapping(value = "/getHistory", produces = "application/json")
	@ResponseBody
	public List<TradeString> getHistory() {
		return history.getHistory();
	}

	@RequestMapping(value = "/remove/{id}")
	public void removeTrade(@PathVariable("id") long id) {
		history.removeTrade(id);
	}
	
	@RequestMapping(value = "/update/{id}", consumes = "application/json")
	public void updateTrade(@PathVariable("id") long id, @RequestBody TradeString trade) {
		history.updateEmitent(trade.getSecName(), trade.getEmitentTitle(), trade.getRegNumber(), trade.getSecId());
		history.updateTrade(trade.getTradeDate(), trade.getOpenPrice(), trade.getClosePrice(), trade.getNumTrades(), trade.getTradeId());
	}	
	
}
