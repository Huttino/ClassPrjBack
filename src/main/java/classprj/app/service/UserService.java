package classprj.app.service;

import classprj.app.model.request.SignUpRequest;
import classprj.app.model.request.UpdatePasswordRequest;
import classprj.app.model.request.UpdateUserRequest;

import java.net.URI;

public interface UserService {

	 boolean checkUserExistence(String username);
	 URI signUp(SignUpRequest request);
	 void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void updateUser(UpdateUserRequest updateUserRequest);
}
