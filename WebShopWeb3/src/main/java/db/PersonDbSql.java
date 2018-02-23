package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.PaswoordGebruiker;
import domain.Person;
import domain.Role;

public class PersonDbSql implements PersonDb {
	private Properties properties;
	private String url;
	
	public PersonDbSql(Properties properties){
		
		try{
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Person get(String personId) {
		Person p = new Person();
		String sql = "SELECT * from person where id=?";
		try( Connection connection = DriverManager.getConnection(url, properties);
			Statement statement = connection.prepareStatement(sql)) {
			((PreparedStatement) statement).setString(1,personId);
			ResultSet result = ((PreparedStatement) statement).executeQuery();
			while(result.next()){
			String id = result.getString("id");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
			String email = result.getString("email");
			String password = result.getString("password");
			String salt = result.getString("salt");
			String role = result.getString("role");
			p = new Person(id, email, password, firstname, lastname, salt, role);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return p;
	}

	@Override
	public List<Person> getAll() {
		List<Person>res = new ArrayList<>();
		ResultSet result;
		try( Connection connection = DriverManager.getConnection(url, properties);
		Statement statement = connection.createStatement()) {
			result = statement.executeQuery("SELECT * from person");
			while(result.next()){
				String id = result.getString("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String email = result.getString("email");
				String password = result.getString("password");
				String salt = result.getString("salt");
				String role = result.getString("role");
				
				Person p = new Person(id, email, password, firstname, lastname, salt, role);
				res.add(p);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return res;
		
	}

	@Override
	public void add(Person person) {
		String sql = "INSERT INTO person(ID, firstname, lastname, email, password, salt, role)\nVALUES(?,?,?,?,?,?,?)";
		try( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)) {
			((PreparedStatement) statement).setString(1,person.getUserid());
			((PreparedStatement) statement).setString(2,person.getFirstName());
			((PreparedStatement) statement).setString(3,person.getLastName());
			((PreparedStatement) statement).setString(4,person.getEmail());
			((PreparedStatement) statement).setString(5,person.getPassword());
			((PreparedStatement) statement).setString(6, person.getSalt());
			// to string is nodig anders werkt de setString van een prepared statement niet, tostring() zal echter niks aan de role veranderen.
			((PreparedStatement) statement).setString(7, person.getRole().toString());
			((PreparedStatement) statement).execute();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		
	}

	@Override
	public void update(Person person) {
		if(person == null){
			throw new DbException("No person given");
		}
		String sql = "Update person SET firstname=?, lastname=?, email=?, password=?, salt=?, role=? where id=?" ;
		try ( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)){
			((PreparedStatement) statement).setString(1,person.getFirstName());
			((PreparedStatement) statement).setString(2,person.getLastName());
			((PreparedStatement) statement).setString(3,person.getEmail());
			((PreparedStatement) statement).setString(4, person.getPassword());
			((PreparedStatement) statement).setString(5, person.getSalt());
			((PreparedStatement) statement).setString(6, person.getRole().toString());
			((PreparedStatement) statement).setString(7,person.getUserid());
			((PreparedStatement) statement).executeUpdate();
			
		}catch(SQLException e){
			throw new DbException(e.getMessage(), e);
		}
		
	}

	@Override
	public void delete(String personId) {
		String sql = "DELETE FROM person WHERE ID=?";
		try ( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)) {
			((PreparedStatement) statement).setString(1,personId);
			((PreparedStatement) statement).executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

}
