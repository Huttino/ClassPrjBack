package ClassPrj.app.Mapper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		toReturn.setAuthorithies(authorities);
		Map<String,Long> mapToadd=new HashMap<>();
		teacher.getHasCreated().forEach(c->{
			mapToadd.put(c.getClassName(), c.getId());
		});
		toReturn.setHasCreated(mapToadd);
		return toReturn;
	}
}
