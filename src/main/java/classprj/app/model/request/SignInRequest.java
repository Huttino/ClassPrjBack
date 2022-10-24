package classprj.app.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignInRequest {

	@NotNull
	@NotEmpty
	@Email
	private String username;
	@NotNull
	@NotEmpty
	private String password;
	
	
	public SignInRequest(
			String username,
			String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
