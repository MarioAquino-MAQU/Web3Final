package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import domain.PaswoordGebruiker;
import domain.Person;

public class Db {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/1TX31?currentSchema=r0635688_web";
		properties.setProperty("user", "r0635688");
		properties.setProperty("password", PaswoordGebruiker.paswoord);
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(url, properties);
		Statement statement = connection.createStatement();
		
		/*String delid = JOptionPane.showInputDialog("Wich id do you want to delete?");
		statement.executeUpdate("DELETE FROM r0635688_web.person WHERE ID='"+delid+"'");*/


			String idd = JOptionPane.showInputDialog("What's the id?");
			String fname = JOptionPane.showInputDialog("What's the firstname?");
			String lname = JOptionPane.showInputDialog("What's the lastname?");
			String emaill = JOptionPane.showInputDialog("What's the email?");
			String pass = JOptionPane.showInputDialog("What's the password?");
			statement.executeUpdate("INSERT INTO r0635688_web.person(ID, firstname, lastname, email, password)\nVALUES('"+idd+"', '"+fname+"', '"+lname+"', '"+emaill+"', '"+pass+"');");


		ResultSet result = statement.executeQuery("SELECT * from person");
		
		while(result.next()){
			String id = result.getString("id");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
			String email = result.getString("email");
			String password = result.getString("password");
			
			Person p = new Person(id, email, password, firstname, lastname);
			
			System.out.println(p.toString());
		}

		
		statement.close();
		connection.close();
		
		
		}
}
