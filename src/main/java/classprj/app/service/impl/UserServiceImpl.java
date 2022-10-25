package classprj.app.service.impl;

import classprj.app.exception.ApiException;
import classprj.app.mapper.StudentMapper;
import classprj.app.mapper.UserMapper;
import classprj.app.model.request.SignUpRequest;
import classprj.app.model.request.UpdatePasswordRequest;
import classprj.app.model.request.UpdateUserRequest;
import classprj.app.repository.StudentRepository;
import classprj.app.repository.UserRepository;
import classprj.app.service.UserService;
import classprj.app.domain.Student;
import classprj.app.domain.User;
import classprj.app.security.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
			throw new ApiException("Username Already in Use",HttpStatus.CONFLICT.value());
		}
		Student toSave=StudentMapper.SignUpRequestToStudent(request, passwordEncoder.encode(request.getPassword()));
		this.studentRepository.save(toSave);
		return URI.create(toSave.getUsername());
	}

	@Override
	public void updatePassword(UpdatePasswordRequest updatePasswordRequest){
		String password=PrincipalUtils.extractPrincipalObject(SecurityContextHolder.getContext().getAuthentication()).getPassword();
		if(!updatePasswordRequest.confirmPassword()){
			throw new ApiException("new Passwords don't match",HttpStatus.BAD_REQUEST.value());
		}
		else if (!this.passwordEncoder.matches(updatePasswordRequest.getOldPassword(),password)){
			throw new ApiException("Wrong old Password",HttpStatus.BAD_REQUEST.value());
		}
		else{
			Optional<User> user=userRepository.findById(PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext()));
			if(user.isEmpty())throw new ApiException("User not found",HttpStatus.NOT_FOUND.value());
			user.get().setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
			userRepository.save(user.get());
		}
	}

	@Override
	public boolean checkUserExistence(String username) {
		return userRepository.existsByUsername(username);
	}


	public void updateUser(UpdateUserRequest updateUserRequest) {
		Long userId= PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		String userUsername=PrincipalUtils.extractPrincipal(SecurityContextHolder.getContext().getAuthentication());
		System.out.println(updateUserRequest.getUsername());
		if(!updateUserRequest.getUsername().equals(userUsername) && userRepository.existsByUsername(updateUserRequest.getUsername())) {
			throw new ApiException("Username already in use", HttpStatus.CONFLICT.value());
		}

		userRepository.findById(userId).ifPresent((x)-> userRepository.save(UserMapper.updateRequestToUser(updateUserRequest,x)));
	}
	
}
