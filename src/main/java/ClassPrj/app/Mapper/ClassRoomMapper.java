package ClassPrj.app.Mapper;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Dto.StudentInClass;
import ClassPrj.app.domain.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom,boolean graded) {
		ClassRoomDTO toReturn = new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName() + " " + classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		List<StudentInClass> members = new ArrayList<>();
		if (classRoom.getMembers() != null&&graded) {
			classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername(),x.getGrade())));
		}
		if(classRoom.getMembers()!=null&&!graded){
			classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername())));
		}
		List<DocumentDTO> toSetDocument = new ArrayList<>();
		if (classRoom.getDocuments() != null) {
			classRoom.getDocuments().forEach(x -> toSetDocument.add(DocumentMapper.entityToDTO(x)));
		}
		toReturn.setUploadedDocuments(toSetDocument);
		toReturn.setMembers(members);
		return toReturn;
	}
	public static ClassRoomDTO entityToDtoTeacher(ClassRoom classRoom){
		ClassRoomDTO toReturn = new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName() + " " + classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		List<StudentInClass> members = new ArrayList<>();
		if (classRoom.getMembers() != null) {
			classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername(),x.getGrade())));
		}
		List<DocumentDTO> toSetDocument = new ArrayList<>();
		if (classRoom.getDocuments() != null) {
			classRoom.getDocuments().forEach(x -> toSetDocument.add(DocumentMapper.entityToDTO(x)));
		}
		toReturn.setUploadedDocuments(toSetDocument);
		toReturn.setMembers(members);
		return toReturn;
	}
}
