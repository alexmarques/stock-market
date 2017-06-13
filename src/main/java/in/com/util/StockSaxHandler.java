package in.com.util;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import in.com.dto.Currency;
import in.com.dto.Entity;
import in.com.dto.Operation;
import in.com.dto.Stock;

public class StockSaxHandler extends DefaultHandler {
	
	private List<Stock> stocks = new ArrayList<>();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Stock stock = null;
	private String content = null;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(qName) {
			case "stock":
				stock = new Stock();
				break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(qName) {
			case "stock":
				stocks.add(stock);
				break;
			case "entity":
				Entity entity = new Entity();
				entity.setName(content);
				stock.setEntity(entity);
				break;
			case "operation":
				stock.setOperation(Operation.getByOperationChar(content));
				break;
			case "agreedExchange":
				stock.setAgreedExchange(BigDecimal.valueOf(Double.parseDouble(content)));
				break;
			case "currency":
				Currency currency = new Currency();
				currency.setCode(content);
				stock.setCurrency(currency);
				break;
			case "instructionDate":
				Instant instructionDate = null;
				try {
					instructionDate = df.parse(content).toInstant();
				} catch (ParseException e1) {
				}
				stock.setInstructionDate(instructionDate.atZone(ZoneId.systemDefault()).toLocalDate());
				break;
			case "settlementDate":
				Instant settlementDate = null;
				try {
					settlementDate = df.parse(content).toInstant();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				stock.setSettlementDate(settlementDate.atZone(ZoneId.systemDefault()).toLocalDate());
				break;
			case "units":
				Long units = Long.parseLong(content);
				stock.setUnits(units);
				break;
			case "pricePerUnit":
				stock.setPricePerUnit(BigDecimal.valueOf(Double.parseDouble(content)));
				break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.content = String.copyValueOf(ch, start, length).trim();
	}
	
	public static List<Stock> getStocksFromXml() {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			System.err.println("error getting an instance of saxParser from saxParserFactory. Exception message: " + e.getMessage());
			System.exit(1);
		}
		StockSaxHandler stockSaxHandler = new StockSaxHandler();
		try {
			saxParser.parse(StockSaxHandler.class.getResourceAsStream("/stocks.xml"), stockSaxHandler);
		} catch (SAXException | IOException e) {
			System.err.println("error parsing xml to get stock information. Exception message: " + e.getMessage());
			System.exit(1);
		}
		return stockSaxHandler.stocks;
	}
}