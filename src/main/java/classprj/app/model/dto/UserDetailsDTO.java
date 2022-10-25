package classprj.app.model.dto;

public class UserDetailsDTO {
	private Long id;
	private String username;
	private String authority;


	public UserDetailsDTO(Long id, String username, String authority) {
		super();
		this.id = id;
		this.username = username;
		this.authority = authority;
	}


	public UserDetailsDTO() {
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
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}



}
