package com.parsermoex.services.Impl;

import java.io.InputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.Trade;
import com.parsermoex.repositories.EmitentRepository;
import com.parsermoex.repositories.TradeRepository;

@Service
public class XmlReader {

	private final TradeRepository tradeRepository;
	private final EmitentRepository emitentRepository;
	private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	
	public XmlReader(TradeRepository tradeRepository, EmitentRepository emitentRepository) {
		this.tradeRepository = tradeRepository;
		this.emitentRepository = emitentRepository;
	}

	public Set<Trade> getXmlHistory(InputStream file) {
		
		Set<Trade> stocks = new HashSet<>();
		try {
			Document doc = normalizeDoc(file);
			NodeList nList = doc.getElementsByTagName("row");

			for (int temp = 0; temp < nList.getLength() - 1; temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) node;
					
					String tradeDate = elem.getAttribute("TRADEDATE");
					String secId = elem.getAttribute("SECID");
					Double numTrades = Double.valueOf(elem.getAttribute("NUMTRADES"));
					Double open = parseDouble(elem.getAttribute("OPEN"));
					Double close = parseDouble(elem.getAttribute("CLOSE"));
					
					Trade s = new Trade(secId, tradeDate, numTrades, open, close);
					tradeRepository.save(s);
					stocks.add(s);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stocks;
	}
	
	public Set<Emitent> getXmlEmitent(InputStream file) {

		Set<Emitent> stocks = new HashSet<>();
		try {
			Document doc = normalizeDoc(file);
			NodeList nList = doc.getElementsByTagName("row");

			for (int temp = 0; temp < nList.getLength() - 1; temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) node;
					
					String secId = elem.getAttribute("secid");
					String secName = elem.getAttribute("name");
					String emitentTitle = elem.getAttribute("emitent_title");
					String regNumber = elem.getAttribute("regnumber");

					Emitent s = new Emitent(secId, secName, emitentTitle, regNumber);
					emitentRepository.save(s);
					stocks.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stocks;
	}
	
	private Document normalizeDoc(InputStream file) {
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		
		return doc;
	}
	
	private double parseDouble(String val) {
		if (val == null || val.isEmpty()) {
			return 0.0;
		} else {
			return Double.parseDouble(val);
		}
	}

}
