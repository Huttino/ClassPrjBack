package ClassPrj.app.Mapper;


import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.TeacherDTO;
import ClassPrj.app.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherMapper {
	public static TeacherDTO EntityToDTO(Teacher teacher) {
		TeacherDTO toReturn=new TeacherDTO();
		toReturn.setId(teacher.getId());
		toReturn.setUsername(teacher.getUsername());
		toReturn.setFirstName(teacher.getFirstName());
		toReturn.setLastName(teacher.getLastName());
		toReturn.setAuthority(teacher.getRole().getRoleName());
		List<ClassRoomDTO> mapToadd=new ArrayList<>();
		teacher.getHasCreated().forEach(c->{
			mapToadd.add(ClassRoomMapper.entityToDtoTeacher(c));
		});
		toReturn.setHasCreated(mapToadd);
		return toReturn;
	}
}
