package in.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.com.dao.mapper.RankingReportRowMapper;
import in.com.dao.mapper.SettledAmountRowMapper;
import in.com.dto.Operation;
import in.com.dto.RankingReport;
import in.com.dto.StockReport;
import in.com.util.DatabaseUtils;

public class StockReportDAO {
	
	public List<StockReport> amountReport(Operation operationFlag) {
		ResultSet rs = null;
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement("SELECT SETTLEMENT_DATE, " +
						                                           " SUM(PRICE_PER_UNIT * UNITS * AGREED_EXCHANGE) AS TRADE_AMOUNT " +
						                                      " FROM STOCK " +
						                                     " WHERE OPERATION = ? " +
						                                  " GROUP BY SETTLEMENT_DATE " +
						                                  " ORDER BY SETTLEMENT_DATE");
			) {
			ps.setString(1, operationFlag.getOperationChar());
			rs = ps.executeQuery();
			List<StockReport> stocks = new ArrayList<>();
			SettledAmountRowMapper settledAmountRowMapper = new SettledAmountRowMapper();
			for(int index = 1; rs.next(); index++) {
				stocks.add(settledAmountRowMapper.mapper(index, rs));
			}
			return stocks;
			
		} catch (SQLException e) {
			System.err.println("could not retrieve stocks from database. Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		return Collections.emptyList();
	}
	
	public List<RankingReport> getRankingReport(Operation operationFlag) {
		
		ResultSet rs = null;
		
		String sql = "SELECT E.NAME, " +
				           " SUM(PRICE_PER_UNIT * UNITS * AGREED_EXCHANGE) AS TRADE_AMOUNT " +
				      " FROM STOCK S " +
				" INNER JOIN ENTITY E ON S.ENTITY_ID = E.ID " +
				      " WHERE OPERATION = ? " +
				   " GROUP BY E.NAME " +
				   " ORDER BY TRADE_AMOUNT DESC";
		
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setString(1, operationFlag.getOperationChar());
			rs = ps.executeQuery();
			List<RankingReport> rankingReport = new ArrayList<>();
			RankingReportRowMapper rankingReportRowMapper = new RankingReportRowMapper(operationFlag);
			for(int index = 1; rs.next(); index++) {
				rankingReport.add(rankingReportRowMapper.mapper(index, rs));
			}
			return rankingReport;
			
		} catch (SQLException e) {
			System.err.println("could not retrieve stocks from database. Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		return Collections.emptyList();
		
	}
}
