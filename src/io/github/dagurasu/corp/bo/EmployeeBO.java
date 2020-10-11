package io.github.dagurasu.corp.bo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.github.dagurasu.corp.model.Employee;

public class EmployeeBO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/corporation";

	static final String USER = "root";
	static final String PASSWORD = "";

	public String createEmployeeTable() {
		Connection conn = null;
		Statement stmt = null;
		String sql;
		String result;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			DatabaseMetaData dbm = (DatabaseMetaData) conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "EMPLOYEES", null);

			if (tables.next()) {
				System.out.println("EMPLOYEES table already exists!!");
				result = "Existing";
			} else {
				System.out.println("Creating table...");

				sql = "create table EMPLOYEES " 
						+ "(ID int not null auto_increment, " 
						+ "FIRST_NAME varchar(100) null, "
						+ "LAST_NAME varchar(100) null, " 
						+ "COMPANY varchar(100) null,"
						+ "REGISTRATION varchar(100) null, "
						+ "SALARY double null," 
						+ "primary key (ID))";
				stmt.executeUpdate(sql);

				System.out.println("EMPLOYEES table created!!");
				result = "Created";
			}

			stmt.close();
			conn.close();
			return result;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error Creating EMPLOYEE table!!");
			return "Error";
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public List<Employee> findAllEmployees() {
		List<Employee> employList = new ArrayList<Employee>();
		Connection conn = null;
		Statement stmt = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = "SELECT * FROM EMPLOYEES";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Employee currentEmployee = new Employee();

				currentEmployee.setId(rs.getInt("ID"));
				currentEmployee.setFirstName(rs.getString("FIRST_NAME"));
				currentEmployee.setLastName(rs.getString("LAST_NAME"));
				currentEmployee.setCompany(rs.getString("COMPANY"));
				currentEmployee.setRegistration(rs.getString("REGISTRATION"));
				currentEmployee.setSalary(rs.getDouble("SALARY"));

				employList.add(currentEmployee);
			}

			rs.close();

			stmt.close();
			conn.close();
			return employList;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error loading EMPLOYEES table!!");
			return employList;
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {

				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	public long insertEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("insertEmployee(): connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			sql = "INSERT INTO EMPLOYEES" 
					+ "(first_name, "
					+ "last_name, "
					+ "company,"
					+ "registration,"
					+ "salary) VALUES"
					+ "(?,?,?,?,?)";

			System.out.println("Creating prepared statemet...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getCompany());
			preparedStatement.setString(4, employee.getRegistration());
			preparedStatement.setDouble(5, employee.getSalary());

			Integer affectedRows = preparedStatement.executeUpdate();
			Long idNewRow;
			if (affectedRows == 0) {
				throw new SQLException("Creating row failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRow = generatedKeys.getLong(1);
					System.out.println("Id of new object: " + idNewRow);
				} else {
					throw new SQLException("Creating row failed, no ID obtained.");
				}
			}

			preparedStatement.close();
			conn.close();
			return idNewRow;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating row in EMPLOYEES table!!");
			return 0;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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

	public long deleteEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("deleteEmployee(): connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			sql = "DELETE FROM EMPLOYEES "
					+ "WHERE ID = ?";

			System.out.println("Creatind prepared statement...");
			preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setLong(1, employee.getId());

			Integer affectRows = preparedStatement.executeUpdate();

			if (affectRows == 0) {
				throw new SQLException("Deleting row failed, no rows affected.");
			} else {
				System.out.println("Object deleted!!");
			}

			preparedStatement.close();
			conn.close();
			return affectRows;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating row in EMPLOYEES table!!");
			return 0;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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

	public long updateEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("updateEmployee(): connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			sql = "UPDATE EMPLOYEES SET "
					+ "first_name=?, "
					+ "last_name=?, "
					+ "company=?, "
					+ "registration=?, "
					+ "salary=? "
					+ "WHERE id=?";

			System.out.println("Creating prepared statement...");
			preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getCompany());
			preparedStatement.setString(4, employee.getRegistration());
			preparedStatement.setDouble(5, employee.getSalary());
			preparedStatement.setLong(6, employee.getId());

			Integer affectRows = preparedStatement.executeUpdate();

			Long idNewRow;
			if (affectRows == 0) {
				throw new SQLException("Updating row failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					idNewRow = generatedKeys.getLong(1);
					System.out.println("od of new object: " + idNewRow);
				} else {
					throw new SQLException("Updating row failed, no ID obtained.");
				}
			}
			preparedStatement.close();
			conn.close();
			return idNewRow;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error updating row in EMPLOYEES table!!");
			return 0;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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
}