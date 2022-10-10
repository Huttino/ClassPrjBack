package ClassPrj.app.Mapper;

import ClassPrj.app.Model.Dto.UserDetailsDTO;
import ClassPrj.app.Model.Request.UpdateUserRequest;
import ClassPrj.app.domain.User;
import ClassPrj.app.security.UserDetailImpl;

public class UserMapper {

	public static UserDetailsDTO userDetailToDto(UserDetailImpl userDetail) {
        if (userDetail == null) {
            return null;
        }
		String role=userDetail.getAuthorities().stream().findFirst().get().getAuthority();
        UserDetailsDTO user = new UserDetailsDTO(userDetail.getId(), userDetail.getUsername() ,role);
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
