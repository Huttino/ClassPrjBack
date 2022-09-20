package ClassPrj.app.Mapper;

import java.util.HashMap;
import java.util.Map;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.domain.ClassRoom;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom) {
		ClassRoomDTO toReturn=new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(TeacherMapper.EntityToDTO(classRoom.getCreator()));
		toReturn.setClassName(classRoom.getClassName());
		Map<String, Long> members=new HashMap<>();
		classRoom.getMembers().forEach(x->{
			members.put(x.getUsername(), x.getId());
		});
		toReturn.setMembers(members);
		return toReturn;
	}
}
