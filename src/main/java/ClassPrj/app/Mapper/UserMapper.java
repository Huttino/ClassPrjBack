package ClassPrj.app.Mapper;

import ClassPrj.app.Model.Dto.UserDetailsDTO;
import ClassPrj.app.Model.Request.UpdateUserRequest;
import ClassPrj.app.domain.User;
import ClassPrj.app.security.UserDetailImpl;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

	public static UserDetailsDTO userDetailToDto(UserDetailImpl userDetail) {
        if (userDetail == null) {
            return null;
        }

        List<String> roles = userDetail.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        UserDetailsDTO user = new UserDetailsDTO(userDetail.getId(), userDetail.getUsername() ,roles);

        return user;
    }

	public static User updateRequestToUser(UpdateUserRequest updateUserRequest, User user) {
		if(updateUserRequest.getFirstName()!=null) {
			user.setFirstName(updateUserRequest.getFirstName());
		}
		if(updateUserRequest.getLastName()!=null) {
			user.setLastName(updateUserRequest.getLastName());
		}
		if(updateUserRequest.getUsername()!=null) {
			user.setUsername(updateUserRequest.getUsername());
		}
		return user;
	}
}
