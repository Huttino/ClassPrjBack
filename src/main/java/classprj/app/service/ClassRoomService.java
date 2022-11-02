package classprj.app.service;

import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.ClassRoomStrippedDTO;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.model.request.NewClassRoomRequest;
import classprj.app.model.request.ScopeFilter;
import classprj.app.model.request.UpdateCoverRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ClassRoomService {
	ClassRoomDTO create(NewClassRoomRequest nr, Long teacherId);
	void join(Long classRoomId,Long userId);
	void leave(Long classRoomId,Long userId);
	void delete(Long classRoomId,Long teacherId);
	ClassRoomDTO getClassById(Long id,Long userId);

    List<ClassRoomStrippedDTO> getAllClass();

    List<ClassRoomDTO> getClassesFrom(Long teacherId);

    void updateCover(UpdateCoverRequest request, Long classId, Long teacherId) throws IOException;

	Set<PublicClassRoomDTO> findByScopes(ScopeFilter filter);
}
