package classprj.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import classprj.app.mapper.UserMapper;
import classprj.app.model.dto.UserDetailsDTO;

public class PrincipalUtils {
	public static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl loggedUser = (UserDetailImpl) authentication.getPrincipal();
            return loggedUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    public static UserDetailImpl extractPrincipalObject(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetailImpl) {
            UserDetailImpl loggedUser = (UserDetailImpl) authentication.getPrincipal();
            return loggedUser;
        } 
        return null;
    }

    public static UserDetailImpl loggerUserDetailsFromContext(SecurityContext ctx) {
        Authentication authentication = ctx.getAuthentication();
		
		UserDetailImpl loggedUser = PrincipalUtils.extractPrincipalObject(authentication);

        return loggedUser;
    }

    public static UserDetailsDTO loggerUserFromContext(SecurityContext ctx) {
        return UserMapper.userDetailToDto(PrincipalUtils.loggerUserDetailsFromContext(ctx));
    }

    public static Long loggerUserIdFromContext(SecurityContext ctx) {
        return PrincipalUtils.loggerUserDetailsFromContext(ctx).getId();
    }
}
