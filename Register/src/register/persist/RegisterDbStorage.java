package register.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import register.ListRegister;
import register.Person;
import register.Register;

public class RegisterDbStorage implements RegisterStorage {
	//NEZABUDNUT stiahnut postgresql jar subor a pridat do build path libraries (hladaj na google "jdbc postgres driver")
	public static final String DRIVER_CLASS = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://localhost/register";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    
    public static final String DROP_QUERY = "DROP TABLE person";
    public static final String CREATE_QUERY = "CREATE TABLE person (id INT PRIMARY KEY, name VARCHAR(255) NOT NULL, telephone VARCHAR(32) NOT NULL)";
    public static final String INSERT_QUERY = "INSERT INTO person (id, name, telephone) VALUES (?, ?, ?)";
    public static final String SELECT_QUERY = "SELECT id, name, telephone FROM person";
    
    public RegisterDbStorage() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
    
    private void dropAndCreateTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
	        stmt.executeUpdate(DROP_QUERY);
	        stmt.close();
    		stmt = con.createStatement();
	        stmt.executeUpdate(CREATE_QUERY);
	        stmt.close();
        } catch (SQLException ex) {
        	//ex.printStackTrace(); //nevypis nic
        }
    }
	
    private void createTable(Connection con) {
    	try {
    		Statement stmt = con.createStatement();
	        stmt = con.createStatement();
	        stmt.executeUpdate(CREATE_QUERY);
	        stmt.close();
        } catch (SQLException ex) {
        	//ex.printStackTrace(); //nevypis nic
        }
    }
    
	
	@Override
	public void save(Register register) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			dropAndCreateTable(con);
			for (int i=0; i<register.getCount(); i++) {
				Person person = register.getPerson(i);
				PreparedStatement stmt = con.prepareStatement(INSERT_QUERY);
		        stmt.setInt(1, i);
		        stmt.setString(2, person.getName());
		        stmt.setString(3, person.getPhoneNumber());
		        stmt.executeUpdate();
		        stmt.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public Register load() {
		Register register = new ListRegister();
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			createTable(con);
			Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(SELECT_QUERY);
	
	        while(rs.next()) {
	        	Person person = new Person(rs.getString(2), rs.getString(3));
	        	register.addPerson(person);
	        }
	        
	        rs.close();
	        stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return register;
	}

}
