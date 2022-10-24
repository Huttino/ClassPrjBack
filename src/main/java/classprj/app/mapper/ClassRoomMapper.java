package classprj.app.mapper;

import classprj.app.model.dto.*;
import classprj.app.domain.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom, boolean notGraded) {
		ClassRoomDTO toReturn = new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName() + " " + classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		List<StudentInClass> members = new ArrayList<>();
		if (classRoom.getMembers() != null&& !notGraded) {
			classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername(),x.getGrade())));
		}
		if(classRoom.getMembers()!=null&& notGraded){
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

	public static ClassInStudent entityToSpecificDTO(ClassRoom classRoom){
		ClassInStudent toReturn= new ClassInStudent();
		toReturn.setClassName(classRoom.getClassName());
		toReturn.setId(classRoom.getId());
		ArrayList<VideoLessonDTO> toSet=new ArrayList<>();
		toReturn.setVideoLessons(toSet);
		classRoom.getLessons().forEach(x->toSet.add( VideoLessonMapper.entityToDTO(x)));
		return toReturn;
	}
}
