package ClassPrj.app.Model.Request;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SignUpRequest {

	@NotNull
    @Email
    private String username;

    @NotNull
    private String password;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;

    
    
	public SignUpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUpRequest(@NotNull @Email String username, @NotNull String password, @NotNull String firstName,
			@NotNull String lastName) {
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
