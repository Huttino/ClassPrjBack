package ClassPrj.app.Service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ClassPrj.app.Mapper.UserMapper;
import ClassPrj.app.Repository.UserRepository;
import ClassPrj.app.domain.User;
import ClassPrj.app.security.UserDetailImpl;

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
		User user=this.userRepository.findByUsername(username).get();
		System.out.println(user);
		return UserDetailImpl.build( this.userRepository.findByUsername(username).get());
	}



}
