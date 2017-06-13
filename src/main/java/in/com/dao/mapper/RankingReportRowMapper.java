package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.dto.Entity;
import in.com.dto.Operation;
import in.com.dto.RankingReport;

public class RankingReportRowMapper implements RowMapper<RankingReport> {
	
	private Operation operation;
	
	public RankingReportRowMapper(Operation operation) {
		this.operation = operation;
	}

	@Override
	public RankingReport mapper(int index, ResultSet rs) throws SQLException {
		RankingReport rankingReport = new RankingReport();
		
		Entity entity = new Entity();
		entity.setName(rs.getString("NAME"));
		rankingReport.setEntity(entity);
		
		rankingReport.setOperation(operation);
		rankingReport.setRankingPosition(index);
		rankingReport.setTradeAmount(rs.getBigDecimal("TRADE_AMOUNT"));
		
		return rankingReport;
	}
}