package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.dto.StockReport;

public class SettledAmountRowMapper implements RowMapper<StockReport> {

	@Override
	public StockReport mapper(int index, ResultSet rs) throws SQLException {
		StockReport stockReport = new StockReport();
		stockReport.setSettlementDate(rs.getDate("SETTLEMENT_DATE").toLocalDate());
		stockReport.setTradeAmount(rs.getBigDecimal("TRADE_AMOUNT"));
		return stockReport;
	}

}
