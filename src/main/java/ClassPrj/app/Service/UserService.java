package ClassPrj.app.Service;

import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;
import ClassPrj.app.Model.Request.UpdateUserRequest;

import java.net.URI;

public interface UserService {

	 boolean checkUserExistence(String username);
	 URI signUp(SignUpRequest request);
	 void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void updateUser(UpdateUserRequest updateUserRequest);
}
