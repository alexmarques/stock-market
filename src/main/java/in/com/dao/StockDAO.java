package in.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import in.com.dao.mapper.StockRowMapper;
import in.com.dto.Stock;
import in.com.util.DatabaseUtils;

public class StockDAO {
	
	private static final String INSERT_STATEMENT = "INSERT INTO STOCK (ENTITY_ID, OPERATION, AGREED_EXCHANGE, CURRENCY_ID, INSTRUCTION_DATE, " +
										     "SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_STATEMENT = "SELECT * FROM STOCK";
	
	private static final StockRowMapper stockRowMapper = new StockRowMapper();
	
	public List<Stock> all() {
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT);
				ResultSet rs = ps.executeQuery();
			) {
			List<Stock> stocks = new ArrayList<>();
			for(int index = 0; rs.next(); index++) {
				stocks.add(stockRowMapper.mapper(index, rs));
			}
			return stocks;
			
		} catch (SQLException e) {
			System.err.println("could not retrieve stocks from database. Exception message: " + e.getMessage());
		}
		return Collections.emptyList();
		
	}
	
	public boolean insert(Stock stock) {
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
			) {
			
			ps.setLong(1, stock.getEntity().getId());
			ps.setString(2, stock.getOperation().getOperationChar());
			ps.setBigDecimal(3, stock.getAgreedExchange());
			ps.setLong(4, stock.getCurrency().getId());
			
			Date instructionDate = Date.from(stock.getInstructionDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			ps.setDate(5, new java.sql.Date(instructionDate.getTime()));
			
			Date settlementDate = Date.from(stock.getSettlementDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			ps.setDate(6, new java.sql.Date(settlementDate.getTime()));
			
			ps.setLong(7, stock.getUnits());
			ps.setBigDecimal(8, stock.getPricePerUnit());
			
			return ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
			System.err.println("error saving stock information into the database. Exception message: " + e.getMessage());
		}
		return false;
	}
}