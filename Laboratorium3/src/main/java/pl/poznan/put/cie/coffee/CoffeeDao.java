package pl.poznan.put.cie.coffee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CoffeeDao {

	private final NamedParameterJdbcTemplate jdbc;

	public CoffeeDao() {
		this.jdbc = new NamedParameterJdbcTemplate(DbUtilities.getDataSource("jdbc:h2:~/test", "SA", ""));
	}

	/**
	 * Returns a coffee with given name.
	 *
	 * @param name coffee name
	 * @return coffee object or null
	 */
	public Coffee get(String name) {
		String sql = "SELECT sup_id, price, sales, total FROM coffees "
				  + "WHERE cof_name = :cof_name";
		MapSqlParameterSource params = new MapSqlParameterSource("cof_name", name);
		return jdbc.query(sql, params, (ResultSetExtractor<Coffee>) rs -> rs.next() ? new Coffee() {{
			setName(name);
			setSupplierId(rs.getInt("sup_id"));
			setPrice(rs.getBigDecimal("price"));
			setSales(rs.getInt("sales"));
			setTotal(rs.getInt("total"));
		}} : null);
	}

	/**
	 * Returns a list of all coffees.
	 *
	 * @return list of all coffees
	 */
	public List<Coffee> getAll() {
		String sql = "SELECT cof_name, sup_id, price, sales, total FROM coffees";
		try {
			return this.jdbc.query(sql, (rs, rowNum) -> new Coffee() {{
				setName(rs.getString("cof_name"));
				setSupplierId(rs.getInt("sup_id"));
				setPrice(rs.getBigDecimal("price"));
				setSales(rs.getInt("sales"));
				setTotal(rs.getInt("total"));
			}});
		} catch (EmptyResultDataAccessException ex) {
			return new ArrayList<>();
		}
	}

	public void update(Coffee c) {
		String sql = "UPDATE coffees "
				  + "SET price = :price, sales = :sales, total = :total "
				  + "WHERE cof_name = :cof_name AND sup_id = :sup_id";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("price", c.getPrice());
		parameters.put("sales", c.getSales());
		parameters.put("total", c.getTotal());
		parameters.put("cof_name", c.getName());
		parameters.put("sup_id", c.getSupplierId());
		jdbc.update(sql, parameters);
	}

	public void delete(String name, int supplierId) {
		String sql = "DELETE FROM coffees "
				+ "WHERE cof_name = :cof_name + AND sup_id = :sup_id";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("cof_name", name);
		parameters.put("sup_id", supplierId);
		jdbc.update(sql, parameters);
	}

	public void create(Coffee c) {
		String sql = "INSERT INTO coffees (cof_name, sup_id, price, sales, total) "
				+ "VALUES ( :cof_name, :sup_id, :price,  :sales,  :total)";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("price", c.getPrice());
		parameters.put("sales", c.getSales());
		parameters.put("total", c.getTotal());
		parameters.put("cof_name", c.getName());
		parameters.put("sup_id", c.getSupplierId());
		jdbc.update(sql, parameters);
	}
}
