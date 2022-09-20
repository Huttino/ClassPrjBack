package ClassPrj.app.Service;

import java.net.URI;

import javax.validation.Valid;

import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;

public interface UserService {

	public boolean checkUserExcistance(String username);
	public URI signUp(SignUpRequest request);
	public void updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
