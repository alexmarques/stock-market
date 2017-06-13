package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	
	public T mapper(int index, ResultSet rs) throws SQLException;

}
