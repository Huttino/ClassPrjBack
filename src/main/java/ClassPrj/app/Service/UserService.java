package ClassPrj.app.Service;

import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;

import java.net.URI;

public interface UserService {

	 boolean checkUserExistence(String username);
	 URI signUp(SignUpRequest request);
	 void updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
