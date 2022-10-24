package classprj.app.mapper;

import classprj.app.model.dto.ClassInStudent;
import classprj.app.model.dto.StudentDTO;
import classprj.app.model.dto.StudentInClass;
import classprj.app.model.ROLEVALUE;
import classprj.app.model.request.SignUpRequest;
import classprj.app.domain.Role;
import classprj.app.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
	
	public static Student SignUpRequestToStudent(SignUpRequest request, String Password) {
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
			memberOf.add(ClassRoomMapper.entityToSpecificDTO(x.getClassRoom()));
		});
		toReturn.setMemberOf(memberOf);
		return toReturn;
    }
	public static StudentInClass EntityToInClass(Student student){
		StudentInClass toReturn=new StudentInClass(student.getId(), student.getUsername());
		return toReturn;
	}

}
