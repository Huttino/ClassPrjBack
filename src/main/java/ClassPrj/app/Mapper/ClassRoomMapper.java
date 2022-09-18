package ClassPrj.app.Mapper;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.domain.ClassRoom;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom) {
		ClassRoomDTO toReturn=new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(TeacherMapper.EntityToDTO(classRoom.getCreator()));
		toReturn.setClassName(classRoom.getClassName());
		return toReturn;
	}
}
