package io.github.dagurasu.corp.bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.DatabaseMetaData;

import io.github.dagurasu.corp.model.Company;

public class CompaniesBO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/corporation";

	static final String USER = "root";
	static final String PASSWORD = "";

	public String createCompaniesTable() {
		Connection conn = null;
		Statement stmt = null;
		String sql;
		String result;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("createCompaniesTable(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			DatabaseMetaData dbm = (DatabaseMetaData) conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "COMPANIES", null);

			if (tables.next()) {
				System.out.println("COMPANIES table already exists!!");
				result = "Existing";
			} else {
				System.out.println("Creating table...");

				sql = "create table COMPANIES (ID int not null auto_increment, NAME varchar(100) null, primary key (ID))";
				stmt.executeUpdate(sql);

				System.out.println("COMPANIES table created!!");
				result = "Created";
			}

			stmt.close();
			conn.close();
			return result;

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			System.out.println("Error creating COMPANIES table!!");
			return "Error";
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public long insertCompany(Company company) {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("createCompaniesTable(): Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			sql = "INSERT INTO COMPANIES (name) VALUES (?)";

			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, company.getName());

			Integer affectRows = preparedStatement.executeUpdate();

			Long idNewRow;
			if (affectRows == 0) {
				throw new SQLException("Creating row failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRow = generatedKeys.getLong(1);
					System.out.println("id of new object: " + idNewRow);
				} else {
					throw new SQLException("Creating row failed, no ID obtained.");
				}
			}

			preparedStatement.close();
			conn.close();
			return idNewRow;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating  row in COMPANIES table!!");
			return 0;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public List<Company> findlAllCompanies() {
		List<Company> companiesList = new ArrayList<Company>();
		Connection conn = null;
		Statement stmt = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			sql = "SELECT * FROM COMPANIES";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Company currentCompany = new Company();

				currentCompany.setId(rs.getLong("ID"));
				currentCompany.setName(rs.getString("NAME"));

				companiesList.add(currentCompany);
			}

			rs.close();
			conn.close();
			return companiesList;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error reading COMPANIES  table!!");
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
