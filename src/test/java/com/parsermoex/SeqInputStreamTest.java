package com.parsermoex;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.parsermoex.entities.Emitent;

public class SeqInputStreamTest {
	
	private String[] securities = new String[]{ "AAPL", "acru" };
	
	private HttpURLConnection conn = null;
	private List<Emitent> emitents = new ArrayList<>();
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	
	@Before
	public void prepareDataForTest() {
		
	}
	
	@Test
	public void test() throws IOException {
		try {
			for (String sec : securities) {
				processUrl("http://iss.moex.com/iss/securities.xml?q=" + sec);	
			}
			
			for(Emitent e : emitents) {
				System.out.println(e);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	private void processUrl(String path) {
		URL addr;
		
		try {
				addr = new URL(path);
				conn = (HttpURLConnection)addr.openConnection();
				
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(conn.getInputStream());
				doc.getDocumentElement().normalize();
				
				NodeList nList = doc.getElementsByTagName("row");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node node = nList.item(temp);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element elem = (Element) node;
						
						String secId = elem.getAttribute("secid");
						String secName = elem.getAttribute("name");
						String emitentTitle = elem.getAttribute("emitent_title");
						String regNumber = elem.getAttribute("regnumber");
						
						Emitent s = new Emitent(secId, secName, emitentTitle, regNumber);
						emitents.add(s);
					}
				}
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();;
		}
		
	}
	
}
