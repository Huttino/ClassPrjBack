package classprj.app.mapper;


import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.TeacherDTO;
import classprj.app.domain.Teacher;

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
		List<ClassRoomDTO> mapToAdd=new ArrayList<>();
		teacher.getHasCreated().forEach(c->{
			mapToAdd.add(ClassRoomMapper.entityToDtoTeacher(c));
		});
		toReturn.setHasCreated(mapToAdd);
		return toReturn;
	}
}
