package ClassPrj.app.Mapper;

import ClassPrj.app.Model.Dto.ClassInStudent;
import ClassPrj.app.Model.Dto.StudentDTO;
import ClassPrj.app.Model.ROLEVALUE;
import ClassPrj.app.Model.Request.SignUpRequest;
import ClassPrj.app.domain.Role;
import ClassPrj.app.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
	
	public static Student SignUpRequestToStudent(SignUpRequest request,String Password) {
		Student toReturn=new Student();
		toReturn.setFirstName(request.getFirstName());
		toReturn.setLastName(request.getLastName());
		toReturn.setRole(new Role( ROLEVALUE.STUDENT));
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
		toReturn.setAuthority(toAdapt.getRole().getRoleName());
		List<ClassInStudent> memberOf=new ArrayList<>();
		toAdapt.getClassList().forEach(x->{
			memberOf.add(new ClassInStudent(x.getClassRoom().getId(), x.getClassRoom().getClassName(),x.getGrade()));
		});
		toReturn.setMemberOf(memberOf);
		return toReturn;
    }
	public static StudentDTO EntityToDtoNoGrade(Student toAdapt){
		StudentDTO toReturn=new StudentDTO();
		toReturn.setUsername(toAdapt.getUsername());
		toReturn.setFirstName(toAdapt.getFirstName());
		toReturn.setLastName(toAdapt.getLastName());
		toReturn.setId(toAdapt.getId());
		toReturn.setAuthority(toAdapt.getRole().getRoleName());
		List<ClassInStudent> memberOf=new ArrayList<>();
		toAdapt.getClassList().forEach(x->{
			memberOf.add(new ClassInStudent(x.getClassRoom().getId(), x.getClassRoom().getClassName()));
		});
		toReturn.setMemberOf(memberOf);
		return toReturn;
	}
}
