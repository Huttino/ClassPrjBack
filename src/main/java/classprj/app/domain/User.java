package classprj.app.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username",nullable = false)
	private String username;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="firstName",nullable = false)
	private String firstName;
	
	@Column(name="lastName",nullable = false)
	private String lastName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Role_Id",nullable = false)
	private Role role;
	
	
	

	public User() {
	}

	public User(Long id, String username, String password, String firstName, String lastName, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1);
		this.lastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1);
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.substring(0,1).toUpperCase()+username.substring(1);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roles=" + role + "]";
	}
	
	
	
	
}	
