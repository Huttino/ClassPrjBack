package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.StudentMapper;
import ClassPrj.app.Mapper.UserMapper;
import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;
import ClassPrj.app.Model.Request.UpdateUserRequest;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.UserRepository;
import ClassPrj.app.Service.UserService;
import ClassPrj.app.domain.Student;
import ClassPrj.app.domain.User;
import ClassPrj.app.security.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;


	
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,StudentRepository studentRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.studentRepository=studentRepository;

	}


	@Override
	public URI signUp(@Valid SignUpRequest request) {
		if(checkUserExistence(request.getUsername())){
			throw new ApiException("Username Already in Use");
		}
		Student toSave=StudentMapper.SignUpRequestToStudent(request, passwordEncoder.encode(request.getPassword()));
		this.studentRepository.save(toSave);
		return URI.create(toSave.getUsername());
	}

	@Override
	public void updatePassword(UpdatePasswordRequest updatePasswordRequest){
		String password=PrincipalUtils.extractPrincipalObject(SecurityContextHolder.getContext().getAuthentication()).getPassword();
		System.out.println(password);
		if(!updatePasswordRequest.confirmPassword()){
			throw new ApiException("new Passwords don't match");
		}
		else if (!passwordEncoder.encode(updatePasswordRequest.getOldPassword()).equals(password)){
			throw new ApiException("Wrong old Password");
		}
		else{
			Optional<User> user=userRepository.findById(PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext()));
			if(user.isEmpty())throw new ApiException("User not found");
			user.get().setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
			userRepository.save(user.get());
		}
	}

	@Override
	public boolean checkUserExistence(String username) {
		return userRepository.existsByUsername(username);
	}


	public void updateUser(UpdateUserRequest updateUserRequest) {
		if(userRepository.existsByUsername(updateUserRequest.getUsername())) {
			throw new ApiException("Username already in use");
		}
		Long userId= PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		userRepository.findById(userId).ifPresent((x)-> userRepository.save(UserMapper.updateRequestToUser(updateUserRequest,x)));
	}
	
}
