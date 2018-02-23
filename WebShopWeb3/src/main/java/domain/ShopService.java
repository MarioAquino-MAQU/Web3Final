package domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import db.PersonDb;
import db.PersonDbInMemory;
import db.PersonDbSql;
import db.ProductDb;
import db.ProductDbInMemory;
import db.ProductDbSql;

public class ShopService {
	PersonDb persondb;
	ProductDb productDb;
	
	public ShopService(Properties properties){
		persondb = new PersonDbSql(properties);
		productDb = new ProductDbSql(properties);
	}
	
	
	public Person getPerson(String personId) {
		return persondb.get(personId);
	}

	public List<Person> getPersons() {
		return persondb.getAll();
	}

	public void addPerson(Person person) {
		persondb.add(person);
	}

	public void updatePersons(Person person) {
		persondb.update(person);
	}

	public void deletePerson(String id) {
		persondb.delete(id);
	}

	
	private ProductDb getProductDb() {
		return productDb;
	}
	
	public List<Product> getProducts() {
		return getProductDb().getAll();
	}
	
	public void addProduct (Product p){
		getProductDb().add(p);
	}
	
	public Product getProduct(int productid){
		return getProductDb().get(productid);
	}
	
	public void updateProduct(Product p){
		getProductDb().update(p);
	}
	public void deleteProduct(int id){
		getProductDb().delete(id);
	}

	public Person getUserIfAuthenticated(String userid, String pw){
		return persondb.get(userid);
	}
}
