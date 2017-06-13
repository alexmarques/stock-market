package in.com.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import in.com.dto.Entity;

public class EntityRowMapper implements RowMapper<Entity> {

	@Override
	public Entity mapper(int index, ResultSet rs) throws SQLException {
		Entity entity = new Entity();
		entity.setId(rs.getLong("ID"));
		entity.setName(rs.getString("NAME"));
		return entity;
	}

}
