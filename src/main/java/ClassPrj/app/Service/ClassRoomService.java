package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.domain.ClassRoom;

import java.util.List;

public interface ClassRoomService {
	public ClassRoom create(String name,String creator);
	public void join(Long classRoomId,Long userId);
	public void leave(Long classRoomId,Long userId);
	public void delete(Long classRoomId,Long teacherId);
	public ClassRoomDTO getClassById(Long id,Long userId);

    List<ClassRoomDTO> getAllClass();
}
