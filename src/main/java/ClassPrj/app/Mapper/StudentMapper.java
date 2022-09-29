package ClassPrj.app.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ClassPrj.app.Model.Dto.ClassInStudent;
import ClassPrj.app.Model.Dto.StudentDTO;
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

    public static StudentDTO EntityToDTO(Student toAdapt) {
		StudentDTO toReturn=new StudentDTO();
		toReturn.setUsername(toAdapt.getUsername());
		toReturn.setFirstName(toAdapt.getFirstName());
		toReturn.setLastName(toAdapt.getLastName());
		toReturn.setId(toAdapt.getId());
		List<String> authorities=new ArrayList();
		toAdapt.getRoles().forEach(x->{
			authorities.add(x.getRoleName());
		});
		toReturn.setAuthorithies(authorities);
		List<ClassInStudent> memberOf=new ArrayList<>();
		toAdapt.getSubscribedTo().forEach(x->{
			memberOf.add(new ClassInStudent(x.getId(),x.getClassName()));
		});
		toReturn.setMemberOf(memberOf);
		return toReturn;
    }
}
