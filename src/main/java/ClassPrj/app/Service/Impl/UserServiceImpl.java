package ClassPrj.app.Service.Impl;

import java.net.URI;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.StudentMapper;
import ClassPrj.app.Model.AuthToken;
import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.UserRepository;
import ClassPrj.app.Service.UserService;
import ClassPrj.app.domain.Student;
import ClassPrj.app.security.JWTUtils;


@Service
public class UserServiceImpl implements UserService {

	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final JWTUtils jwtUtils;
	
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,StudentRepository studentRepository,JWTUtils jwtUtils) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.studentRepository=studentRepository;
		this.jwtUtils=jwtUtils;
	}

	public URI signUp(@Valid SignUpRequest request) {
		if(checkUserExcistance(request.getUsername())){
			throw new ApiException("Username Already in Use");
		}
		Student toSave=StudentMapper.SignUpRequestToStudent(request, passwordEncoder.encode(request.getPassword()));
		this.studentRepository.save(toSave);
		return URI.create(toSave.getUsername());
	}

	public boolean checkUserExcistance(String username) {
		return userRepository.existsByUsername(username);
	}
	
}
