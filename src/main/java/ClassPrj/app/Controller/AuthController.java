package ClassPrj.app.Controller;

import ClassPrj.app.Model.AuthToken;
import ClassPrj.app.Model.Request.SignInRequest;
import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.Service.Impl.RoleServiceImpl;
import ClassPrj.app.Service.Impl.UserDetailsServiceImpl;
import ClassPrj.app.Service.Impl.UserServiceImpl;
import ClassPrj.app.security.JWTUtils;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final UserServiceImpl userServiceImpl;
	private final RoleServiceImpl roleServiceImpl;
	private final JWTUtils jwtUtils;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl,
						  RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder,
						  JWTUtils jwtUtils) {
		Assert.notNull(authenticationManager, "Injected AuthenticationManager instance cannot be null!");
		Assert.notNull(userDetailsServiceImpl, "Injected UserRepository instance cannot be null!");
		Assert.notNull(roleServiceImpl, "Injected ServiceRolesImpl instance cannot be null!");
		Assert.notNull(passwordEncoder, "Injected PasswordEncoder instance cannot be null!");
		Assert.notNull(userServiceImpl, "Injected ServiceUserImpl instance cannot be null!");
		Assert.notNull(jwtUtils, "Injected JwtUtils instance cannot be null!");
		this.authenticationManager = authenticationManager;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.roleServiceImpl = roleServiceImpl;
		this.jwtUtils = jwtUtils;
		this.userServiceImpl = userServiceImpl ;
	}
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthToken> signup(@Valid @RequestBody SignUpRequest request){
		URI created= userServiceImpl.signUp(request);
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		ArrayList<String> tokens=jwtUtils.generateJwtTokens(authentication);
		System.out.println(tokens);
		return ResponseEntity.created(created).body(new AuthToken(tokens.get(0),tokens.get(1)));
	}
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthToken> signin(@Valid @RequestBody SignInRequest request){
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		ArrayList<String> tokens=jwtUtils.generateJwtTokens(authentication);
		System.out.println(tokens);
		return ResponseEntity.ok(new AuthToken(tokens.get(0),tokens.get(1)));
	}
}
