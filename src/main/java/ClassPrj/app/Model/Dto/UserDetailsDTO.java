package ClassPrj.app.Model.Dto;

import java.util.List;

public class UserDetailsDTO {
	private Long id;
	private String username;
	private List<String> authorities;


	public UserDetailsDTO(Long id, String username, List<String> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.authorities = authorities;
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
	public List<String> getAuthorithies() {
		return authorities;
	}
	public void setAuthorithies(List<String> authorithies) {
		this.authorities = authorithies;
	}



}
