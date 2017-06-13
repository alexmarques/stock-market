package in.com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import in.com.dao.CurrencyDAO;
import in.com.dao.EntityDAO;
import in.com.dao.StockDAO;
import in.com.dto.Stock;

public class DatabaseUtils {
	
	private static final StockDAO stockRepository = new StockDAO();
	private static final EntityDAO entityRepository = new EntityDAO();
	private static final CurrencyDAO currencyRepository = new CurrencyDAO();
	
	private static DatabaseUtils INSTANCE = null;
	
	private DatabaseUtils() {}
	
	public static DatabaseUtils getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new DatabaseUtils();
			INSTANCE.createTables();
			INSTANCE.fillUpTables();
		}
		return INSTANCE;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:hsqldb:mem:stock_db", "sa", "");
		} catch (SQLException e) {
			System.err.println("could not get a database connections. Exception message: " + e.getMessage());
		}
		return null;
	}
	
	private void createTables() {
		System.out.println("creating database tables...");
		InputStream is = this.getClass().getResourceAsStream("/CREATE_TABLE.SQL");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder sb = new StringBuilder();
		try {
			while(br.ready()) {
				String line = br.readLine();
				if(line != null && line.isEmpty()) {
					Connection conn = getConnection();
					try {
						conn.prepareStatement(sb.toString()).executeUpdate();
						sb.delete(0, sb.length());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					sb.append(line);
				}
			}
		} catch (IOException e) {
			System.err.println("error creating database tables. Exception message: " + e.getMessage());
			return;
		}
		
		System.out.println("database tables created successfully!");
	}
	
	private void fillUpTables() {
		System.out.println("inserting values into the database...");
		List<Stock> stocks = StockSaxHandler.getStocksFromXml();
		if(stocks != null && stocks.isEmpty()) {
			System.err.println("no stock information found.");
			return;
		}
		for(Stock stock : stocks) {
			entityRepository.insert(stock.getEntity());
			currencyRepository.insert(stock.getCurrency());
			stockRepository.insert(stock);
		}
		System.out.println("values inserted successfully!");
	}
	
	public static void closeQuietly(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
		}
	}
}