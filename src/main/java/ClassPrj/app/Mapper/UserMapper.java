package ClassPrj.app.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;

import ClassPrj.app.Model.Dto.UserDetailsDTO;
import ClassPrj.app.domain.User;
import ClassPrj.app.security.UserDetailImpl;

public class UserMapper {

	public static UserDetailsDTO userDetailToDto(UserDetailImpl userDetail) {
        if (userDetail == null) {
            return null;
        }

        List<String> roles = userDetail.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        UserDetailsDTO user = new UserDetailsDTO(userDetail.getId(), userDetail.getUsername() ,roles);

        return user;
    }
}
