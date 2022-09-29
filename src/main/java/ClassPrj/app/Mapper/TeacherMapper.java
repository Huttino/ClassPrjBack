package ClassPrj.app.Mapper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.TeacherDTO;
import ClassPrj.app.domain.Teacher;

public class TeacherMapper {
	public static TeacherDTO EntityToDTO(Teacher teacher) {
		TeacherDTO toReturn=new TeacherDTO();
		toReturn.setId(teacher.getId());
		toReturn.setUsername(teacher.getUsername());
		toReturn.setFirstName(teacher.getFirstName());
		toReturn.setLastName(teacher.getLastName());
		List<String> authorities=new ArrayList();
		teacher.getRoles().forEach(x->{
			authorities.add(x.getRoleName());
		});
		toReturn.setAuthorities(authorities);
		List<ClassRoomDTO> mapToadd=new ArrayList<>();
		teacher.getHasCreated().forEach(c->{
			mapToadd.add(ClassRoomMapper.entityToDto(c));
		});
		toReturn.setHasCreated(mapToadd);
		return toReturn;
	}
}
