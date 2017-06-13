package in.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.com.dao.mapper.CurrencyMapper;
import in.com.dto.Currency;
import in.com.util.DatabaseUtils;

public class CurrencyDAO {
	
	private static final CurrencyMapper currencyMapper = new CurrencyMapper();
	
	private static final String INSERT_STATEMENT = "INSERT INTO CURRENCY (CODE) VALUES (?)";
	private static final String SELECT_STATEMENT_BY_ID = "SELECT * FROM CURRENCY WHERE ID = ?";
	
	public Currency getById(Long id) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtils.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT_BY_ID);
			){
			ps.setLong(1, id);
			rs = ps.executeQuery();
			Currency currency = null;
			if(rs.next()) {
				currency = currencyMapper.mapper(1, rs);
			}
			return currency;
		} catch (SQLException e) {
			System.err.println("error getting currency with id = " + id + ". Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		
		return null;
		
	}
	
	public Currency insert(Currency currency) {
		ResultSet rs = null;
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setString(1, currency.getCode());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				currency.setId(rs.getLong(1));
			}
			
		} catch (SQLException e) {
			System.err.println("error saving currency information into the database. Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		return currency;
	}

}
