package classprj.app.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
public class SignUpRequest {

	@NotEmpty
	@NotNull
    @Email
    private String username;

    @NotEmpty
	@NotNull
    private String password;
    
    @NotEmpty
	@NotNull
    private String firstName;
    
    @NotEmpty
	@NotNull
    private String lastName;

    
    
	public SignUpRequest() {
		super();
	}

	public SignUpRequest(
			String username,
			String password,
			String firstName,
			String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
}
