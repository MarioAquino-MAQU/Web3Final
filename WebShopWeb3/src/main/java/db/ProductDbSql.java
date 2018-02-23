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
import domain.Product;

public class ProductDbSql implements ProductDb {
	
	private Properties properties;
	private String url;
	
	public ProductDbSql(Properties properties) {
		try{
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		}catch(ClassNotFoundException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Product get(int id) {
		Product p = new Product();
		String sql = "SELECT * from product where productid=?";
		try( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)) {
				((PreparedStatement) statement).setInt(1,id);
				ResultSet result = ((PreparedStatement) statement).executeQuery();
				while(result.next()){
				int pid = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				p = new Product(pid, name, description, price);
				}
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
			return p;
	}

	@Override
	public List<Product> getAll() {
		List<Product>res = new ArrayList<>();
		ResultSet result;
		try( Connection connection = DriverManager.getConnection(url, properties);
		Statement statement = connection.createStatement()) {
			result = statement.executeQuery("SELECT * from product");
			while(result.next()){
				int pid = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				Product p = new Product(pid, name, description, price);
				res.add(p);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return res;
	}

	@Override
	public void add(Product product) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO product(name, description, price)\nVALUES(?,?,?);";
		try {
			connection = DriverManager.getConnection(url, properties);
			statement = connection.prepareStatement(sql);
			statement.setString(1,product.getName());
			statement.setString(2,product.getDescription());
			statement.setDouble(3,product.getPrice());
			statement.execute();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}finally{
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		}
		
	}

	@Override
	public void update(Product product) {
		if(product == null){
			throw new DbException("No product given");
		}
		String sql = "Update product SET name=?, description=?, price=? where productid=?";
		try ( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)){
			((PreparedStatement) statement).setString(1, product.getName());
			((PreparedStatement) statement).setString(2, product.getDescription());
			((PreparedStatement) statement).setDouble(3, product.getPrice());
			((PreparedStatement) statement).setInt(4, product.getProductId());
			((PreparedStatement) statement).executeUpdate();
			
		}catch(SQLException e){
			throw new DbException(e.getMessage(), e);
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM product WHERE productid=?";
		try ( Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.prepareStatement(sql)) {
			((PreparedStatement) statement).setInt(1, id);
			((PreparedStatement) statement).executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		
	}

}
