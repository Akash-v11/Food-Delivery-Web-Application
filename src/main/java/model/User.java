package model; 
 
import java.time.LocalDateTime;
 
public class User {
	private int uid;
	private String username;
	private String email;
	private String password;
	private String mobile;
	private LocalDateTime accCreated;
	private LocalDateTime lastlogin;
	
	// Constructors
	public User() {
	}

	public User(int uid, String username, String email, String password, String mobile, LocalDateTime accCreated,
			LocalDateTime lastlogin) {
		super();
		this.uid = uid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.accCreated = accCreated;
		this.lastlogin = lastlogin;
	}

	public User(String username, String email, String password, String mobile, LocalDateTime accCreated, LocalDateTime lastlogin) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.accCreated = accCreated;
		this.lastlogin = lastlogin;
	}

	
	
	 

	public User(int uid, String username, String email, String password, String mobile) {
		super();
		this.uid = uid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public User(String username, String email, String password, String mobile) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public User(String email, int uid) {
		super();
		this.email = email;
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDateTime getAccCreated() {
		return accCreated;
	}

	public void setAccCreated(LocalDateTime accCreated) {
		this.accCreated = accCreated;
	}

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Override
	public String toString() {
		return  uid + " " + username + " " + email + " " + password + " " + mobile + " " + accCreated + " " + lastlogin;
	}

	  
}
