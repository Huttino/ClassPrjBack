package ClassPrj.app.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ClassPrj.app.domain.*;

public class UserDetailImpl implements UserDetails  {
	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	 public UserDetailImpl(Long id, String username, String password,
	            Collection<? extends GrantedAuthority> authorities) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.authorities = authorities;
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public static UserDetailImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

        return new UserDetailImpl(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
