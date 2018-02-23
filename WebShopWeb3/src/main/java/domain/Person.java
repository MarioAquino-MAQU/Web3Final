package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private Role role;

	public Person(String userid, String email, String password, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setSalt();
		setRole(Role.Customer);
		
	}
	
	public Person(String userid, String email, String password, String firstName, String lastName, String salt, String role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setSalt(salt);
		this.role = Role.valueOf(role);
		
	}
	
	public Person() {
		setSalt();
		setRole(Role.Customer);
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt() {
		SecureRandom random = new SecureRandom();
		byte [] seed = random.generateSeed(20);
		this.salt = new BigInteger(1,seed).toString(16);
	}
	
	public void setSalt(String salt){
		this.salt = salt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new DomainException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new DomainException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new DomainException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		return getPassword().equals(password);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new DomainException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new DomainException("No last name given");
		}
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
	
	
	public void setPassWordHashed(String password){
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		password = hashPassword(password);
		this.password = password;
	}
	
	private String hashPassword(String password){
		MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage());
		}
		crypt.reset();
		byte[] passwordbytes;
		try {
			passwordbytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new DomainException(e.getMessage());
		}
		crypt.update(passwordbytes);
		crypt.update(getSalt().getBytes());
		byte[] digest = crypt.digest();
		BigInteger digestAsBigInteger = new BigInteger(1, digest);
		return digestAsBigInteger.toString(16);
	}
	
	
}
