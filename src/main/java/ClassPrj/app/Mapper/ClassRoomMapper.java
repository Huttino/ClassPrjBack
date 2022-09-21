package ClassPrj.app.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.domain.ClassRoom;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom) {
		ClassRoomDTO toReturn=new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName()+" "+classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		Map<String, Long> members=new HashMap<>();
		classRoom.getMembers().forEach(x->{
			members.put(x.getUsername(), x.getId());
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
