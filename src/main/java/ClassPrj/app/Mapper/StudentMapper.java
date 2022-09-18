package ClassPrj.app.Mapper;

import java.util.ArrayList;
import java.util.List;

import ClassPrj.app.Model.ROLEVALUE;
import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.domain.Role;
import ClassPrj.app.domain.Student;

public class StudentMapper {
	
	public static Student SignUpRequestToStudent(SignUpRequest request,String Password) {
		Student toReturn=new Student();
		toReturn.setFirstName(request.getFirstName());
		toReturn.setLastName(request.getLastName());
		List<Role> toAdd=new ArrayList<Role>();
		toAdd.add(new Role(ROLEVALUE.STUDENT));
		toReturn.setRoles(toAdd);
		toReturn.setUsername(request.getUsername());
		toReturn.setPassword(Password);
		return toReturn;
	}
}
