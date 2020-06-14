package com.parsermoex.controllers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.Trade;
import com.parsermoex.entities.TradeString;
import com.parsermoex.services.Impl.History;
import com.parsermoex.services.Impl.XmlReader;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppController {

	@Autowired
	private XmlReader xmlReader;
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
	
	@RequestMapping(value = "/uploadXML", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void parseXmlData(@RequestParam("file") MultipartFile[] file) {
		try {
			for (MultipartFile item : file) {
				String[] fileType = item.getOriginalFilename().split("_");
				InputStream path = new ByteArrayInputStream(item.getBytes());

				switch (fileType[0]) {
					case "history":
						Set<Trade> trade = xmlReader.getXmlHistory(path);
						trades.addAll(trade);
						break;
					case "securities":
						Set<Emitent> emitent = xmlReader.getXmlEmitent(path);
						emitents.addAll(emitent);
						break;
					default:
						break;
				}
			}
			getMissingEmitents(history.getMissingEmitents());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getMissingEmitents(List<Emitent> missedEmitents) {
		for(Emitent item : missedEmitents) {
			String path = "http://iss.moex.com/iss/securities.xml?q=" + item.getSecId();
			HttpURLConnection conn = null;
			try {
				URL addr = new URL(path);
				conn = (HttpURLConnection)addr.openConnection();
				Set<Emitent> emitent = xmlReader.getXmlEmitent(conn.getInputStream());
				emitents.addAll(emitent);

			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				conn.disconnect();
			}
		}
	}
	
}
