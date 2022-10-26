package classprj.app.service;

import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.ClassRoomStrippedDTO;
import classprj.app.model.request.UpdateCoverRequest;

import java.io.IOException;
import java.util.List;

public interface ClassRoomService {
	ClassRoomDTO create(String name,Long teacherId);
	void join(Long classRoomId,Long userId);
	void leave(Long classRoomId,Long userId);
	void delete(Long classRoomId,Long teacherId);
	ClassRoomDTO getClassById(Long id,Long userId);

    List<ClassRoomStrippedDTO> getAllClass();

    List<ClassRoomDTO> getClassesFrom(Long teacherId);

    void updateCover(UpdateCoverRequest request, Long classId, Long teacherId) throws IOException;
}
