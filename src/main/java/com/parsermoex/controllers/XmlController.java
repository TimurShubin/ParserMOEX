package com.parsermoex.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.parsermoex.entities.Emitent;
import com.parsermoex.services.Impl.History;
import com.parsermoex.services.Impl.XmlReader;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class XmlController {

	@Autowired
	private XmlReader xmlReader;
	@Autowired
	private History history;
	
	@RequestMapping(value = "/uploadXML", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void parseXmlData(@RequestParam("file") MultipartFile[] file) {
		try {
			for (MultipartFile item : file) {
				String[] fileType = item.getOriginalFilename().split("_");
				InputStream path = new ByteArrayInputStream(item.getBytes());

				switch (fileType[0]) {
					case "history":
						xmlReader.getXmlHistory(path);
						break;
					case "securities":
						xmlReader.getXmlEmitent(path);
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
		for (Emitent item : missedEmitents) {			
			 String path = "http://iss.moex.com/iss/securities.xml?q=" + item.getSecId();
			 CompletableFuture<Set<Emitent>> r = getPage(path);
			 CompletableFuture.allOf(r).join();
		}
	}
	
	@Async("asyncExecutor")
	private CompletableFuture<Set<Emitent>> getPage(String path) {
		HttpURLConnection conn = null;
		try {
			URL addr = new URL(path);
			conn = (HttpURLConnection)addr.openConnection();
			CompletableFuture<Set<Emitent>> emitent = xmlReader.getXmlEmitent(conn.getInputStream());
			return emitent;
		 } catch (Exception e) {
			e.printStackTrace();
			return null;
		 } finally {
			conn.disconnect();
		 }
	}
}
