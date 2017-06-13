package in.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.com.dao.mapper.EntityRowMapper;
import in.com.dto.Entity;
import in.com.util.DatabaseUtils;

public class EntityDAO {
	
	private static final EntityRowMapper entityRowMapper = new EntityRowMapper();
	
	private static final String SELECT_STATEMENT_BY_ID = "SELECT * FROM ENTITY WHERE ID = ?";
	private static final String INSERT_STATEMENT = "INSERT INTO ENTITY (NAME) VALUES(?)";
	
	public Entity findById(Long id) {
		ResultSet rs = null;
		try (
				Connection conn = DatabaseUtils.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT_BY_ID);
			) {
			Entity entity = null;
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				entity = entityRowMapper.mapper(1, rs);
			}
			return entity;
		} catch(SQLException e) {
			System.err.println("could not retrieve entity from database. Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		return null;
	}
	
	public Entity insert(Entity entity) {
		ResultSet rs = null;
		try(
				Connection conn = DatabaseUtils.getInstance().getConnection(); 
				PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setString(1, entity.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				entity.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			System.err.println("error saving entity information into the database. Exception message: " + e.getMessage());
		} finally {
			DatabaseUtils.closeQuietly(rs);
		}
		return entity;
	}

}
