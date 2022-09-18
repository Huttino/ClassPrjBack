package ClassPrj.app.Service;

import java.net.URI;

import javax.validation.Valid;

import ClassPrj.app.Model.Request.SignUpRequest;

public interface UserService {

	public boolean checkUserExcistance(String username);
	public URI signUp(SignUpRequest request);
}
