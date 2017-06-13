package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.dto.Currency;

public class CurrencyMapper implements RowMapper<Currency> {

	@Override
	public Currency mapper(int index, ResultSet rs) throws SQLException {
		Currency currency = new Currency();
		currency.setId(rs.getLong("id"));
		currency.setCode(rs.getString("CODE"));
		return currency;
	}

}
