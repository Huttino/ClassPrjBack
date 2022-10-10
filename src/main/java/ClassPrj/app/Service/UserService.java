package ClassPrj.app.Service;

import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;

import java.net.URI;

public interface UserService {

	public boolean checkUserExistence(String username);
	public URI signUp(SignUpRequest request);
	public void updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
