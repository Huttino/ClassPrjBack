package classprj.app.mapper;

import classprj.app.model.dto.UserDetailsDTO;
import classprj.app.model.request.UpdateUserRequest;
import classprj.app.domain.User;
import classprj.app.security.UserDetailImpl;

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
