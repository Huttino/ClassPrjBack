package classprj.app.service.impl;

import classprj.app.exception.ApiException;
import classprj.app.repository.UserRepository;
import classprj.app.domain.User;
import classprj.app.security.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=this.userRepository.findByUsername(username);
		if(user.isEmpty())throw new ApiException("User not found", HttpStatus.NOT_FOUND.value());
		return UserDetailImpl.build(user.get());
	}



}
