package classprj.app.controller;

import classprj.app.model.AuthToken;
import classprj.app.model.request.SignInRequest;
import classprj.app.model.request.SignUpRequest;
import classprj.app.service.UserService;
import classprj.app.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JWTUtils jwtUtils;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserService userService,
						  JWTUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.userService = userService ;
	}
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthToken> signup(@Valid @RequestBody SignUpRequest request){
		URI created= userService.signUp(request);
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
