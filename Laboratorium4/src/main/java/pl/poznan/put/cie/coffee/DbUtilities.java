package pl.poznan.put.cie.coffee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbUtilities {

	private static final String SQL_ESCAPE_CHARS = "[\\\\\\%\\_]";
	private static final Pattern SQL_PATTERN = Pattern.compile(SQL_ESCAPE_CHARS);
	private static final String REPLACEMENT_STRING = "\\\\$0";
	private static final Lock LOCK = new ReentrantLock();
	private static volatile DataSource dataSource = null;

	/**
	 * Return a simple implementation of the standard JDBC DataSource interface.
	 *
	 * @param url the JDBC URL to use for accessing the DriverManager
	 * @return data source
	 */
	public static DataSource getDataSource(String url) {
		if (dataSource != null) {
			return dataSource;
		}
		try {
			LOCK.lock();
			if (dataSource != null) {
				return dataSource;
			}
			dataSource = new DriverManagerDataSource(url);
		} finally {
			LOCK.unlock();
		}
		return dataSource;
	}


	/**
	 * Return a simple implementation of the standard JDBC DataSource interface.
	 *
	 * @param url the JDBC URL to use for accessing the DriverManager
	 * @param username the JDBC username to use for accessing the DriverManager
	 * @param password the JDBC password to use for accessing the DriverManager
	 * @return data source
	 */
	public static DataSource getDataSource(String url, String username, String password) {
		if (dataSource != null) {
			return dataSource;
		}
		try {
			LOCK.lock();
			if (dataSource != null) {
				return dataSource;
			}
			dataSource = new DriverManagerDataSource(url, username, password);
		} finally {
			LOCK.unlock();
		}
		return dataSource;
	}

	/**
	 * Escapes the characters in a String to be suitable to pass to an SQL query.
	 * Escape character is '{@code \}'. For example:
	 * <pre><code>This is % an example String</code></pre> is changed into:
	 * <pre><code>This is \% an example String</code></pre>
	 *
	 * @param input input string
	 * @return escaped string
	 */
	public static String escapeSql(String input) {
		if (input == null) {
			return "";
		}
		return SQL_PATTERN.matcher(input).replaceAll(REPLACEMENT_STRING);
	}
}
