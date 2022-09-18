package ClassPrj.app.security;

import io.jsonwebtoken.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class JWTUtils {

	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.millis}")
    private int expirationMillis;

    public ArrayList<String> generateJwtTokens(Authentication authentication) {
        ArrayList<String> tokens = new ArrayList<>();
    	UserDetailImpl principal = (UserDetailImpl) authentication.getPrincipal();
        Date now = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        tokens.add(Jwts.builder()
                .setClaims(map)
                .setSubject(principal.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationMillis * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact());
        
        tokens.add(this.generateJwtRefreshToken(authentication));
        
        return tokens;
    }
    
    
    public String generateJwtRefreshToken(Authentication authentication) {
        UserDetailImpl principal = (UserDetailImpl) authentication.getPrincipal();
        Date now = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return Jwts.builder()
        		.setClaims(map)
                .setSubject(principal.getUsername())
                .setIssuedAt(now)
                //settato il refresh ogni 30 minuti
                .setExpiration(new Date(System.currentTimeMillis() + 30*60*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    
    public Boolean adminCheck (String token) {
    	Boolean admin = false;
    	List<Object> rolesObject=new ArrayList<>();
    	Claims claim = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    	claim.forEach((t, u) ->{rolesObject.add(u);});
    	
    	List<String> roleString=new ArrayList<>();
    	rolesObject.forEach(x->{
    		roleString.add((String)x);
    	});
    	for (String role : roleString)
    	{
    		if (role=="TEACHER")
    		{
    			admin=true;
    		}
    	}
    	return admin;
    }
    
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
