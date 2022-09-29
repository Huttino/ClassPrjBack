package ClassPrj.app.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Dto.StudentInClass;
import ClassPrj.app.domain.ClassRoom;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom) {
		ClassRoomDTO toReturn=new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName()+" "+classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		List<StudentInClass> members=new ArrayList<>();
		classRoom.getMembers().forEach(x->{
			members.add(new StudentInClass(x.getId(),x.getUsername()));
		});
		List<DocumentDTO> toSetDocument=new ArrayList();
		classRoom.getDocuments().forEach(x->{
			toSetDocument.add(DocumentMapper.entityToDTO(x));
		});
		toReturn.setUploadedDocuments(toSetDocument);
		toReturn.setMembers(members);
		return toReturn;
	}
}
