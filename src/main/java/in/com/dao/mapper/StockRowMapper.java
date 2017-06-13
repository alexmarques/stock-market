package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.dao.CurrencyDAO;
import in.com.dao.EntityDAO;
import in.com.dto.Currency;
import in.com.dto.Operation;
import in.com.dto.Stock;

public class StockRowMapper implements RowMapper<Stock> {
	
	private EntityDAO entityRepository = new EntityDAO();
	private CurrencyDAO currencyRepository = new CurrencyDAO();
	
	@Override
	public Stock mapper(int index, ResultSet rs) throws SQLException {
		
		Stock stock = new Stock();
		
		stock.setId(rs.getLong("ID"));
		
		stock.setEntity(entityRepository.findById(rs.getLong("ENTITY_ID")));
		
		stock.setOperation(Operation.getByOperationChar(rs.getString("OPERATION")));
		
		stock.setAgreedExchange(rs.getBigDecimal("AGREED_EXCHANGE"));
		
		Currency currency = currencyRepository.getById(rs.getLong("CURRENCY_ID"));
		stock.setCurrency(currency);

		stock.setInstructionDate(rs.getDate("INSTRUCTION_DATE").toLocalDate());
		
		stock.setSettlementDate(rs.getDate("SETTLEMENT_DATE").toLocalDate());
		
		stock.setUnits(rs.getLong("UNITS"));
		
		stock.setPricePerUnit(rs.getBigDecimal("PRICE_PER_UNIT"));
		
		return stock;
	}

}
