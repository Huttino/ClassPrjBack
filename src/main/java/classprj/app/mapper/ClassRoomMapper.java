package classprj.app.mapper;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Teacher;
import classprj.app.model.dto.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ClassRoomMapper {
	public static ClassRoomDTO entityToDto(ClassRoom classRoom) {
		ClassRoomDTO toReturn = new ClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setCreator(classRoom.getCreator().getFirstName() + " " + classRoom.getCreator().getLastName());
		toReturn.setClassName(classRoom.getClassName());
		List<StudentInClass> members = new ArrayList<>();
		if(classRoom.getMembers() != null)
			if(classRoom.getMembers().stream().allMatch(x->x.getGrade()==null)) {
				classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername(),x.getGrade())));
			}
			else{
				classRoom.getMembers().forEach(x -> members.add(new StudentInClass(x.getStudent().getId(), x.getStudent().getUsername())));
			}
		List<DocumentDTO> toSetDocument = new ArrayList<>();
		if (classRoom.getDocuments() != null) {
			classRoom.getDocuments().forEach(x -> toSetDocument.add(DocumentMapper.entityToDTO(x)));
		}
		toReturn.setUploadedDocuments(toSetDocument);
		toReturn.setMembers(members);
		ArrayList<VideoLessonDTO> toSet=new ArrayList<>();
		classRoom.getLessons().forEach(
				x->toSet.add(VideoLessonMapper.entityToDTO(x))
		);
		toReturn.setLessons(toSet);
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
		ArrayList<VideoLessonDTO> toSet=new ArrayList<>();
		if(classRoom.getLessons()!=null)
		classRoom.getLessons().forEach(
				x->toSet.add(VideoLessonMapper.entityToDTO(x))
		);
		toReturn.setLessons(toSet);
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

	public static ClassRoomStrippedDTO entityToStrippedDto(ClassRoom x, boolean b) {
		ClassRoomStrippedDTO toReturn=new ClassRoomStrippedDTO();
		toReturn.setGraded(b);
		toReturn.setId(x.getId());
		toReturn.setClassName(x.getClassName());
		Teacher creator=x.getCreator();
		toReturn.setCreator(creator.getFirstName()+" "+creator.getLastName());
		toReturn.setNumberOfLessons(x.getLessons().size());
		toReturn.setNumberOfMembers(x.getMembers().size());
		toReturn.setNumberOfDocuments(x.getDocuments().size());
		toReturn.setScopesId(new HashSet<Long>());
		if(x.getScopes()!=null){
			x.getScopes().forEach(
					y->{
						toReturn.getScopesId().add(y.getId());
					}
			);
		}
		return toReturn;
	}

	public static PublicClassRoomDTO entityToPublicDTO(ClassRoom classRoom){
		PublicClassRoomDTO toReturn=new PublicClassRoomDTO();
		toReturn.setId(classRoom.getId());
		toReturn.setTitle(classRoom.getClassName());
		toReturn.setDescription(classRoom.getDescription());
		if(classRoom.getScopes().size()>0){
			List<ScopeDto> scopes=new ArrayList<ScopeDto>();
			classRoom.getScopes().forEach(x->scopes.add(ScopeMapper.entityToDto(x)));
			toReturn.setScopes(scopes);
		}

		return toReturn;
	}
}
