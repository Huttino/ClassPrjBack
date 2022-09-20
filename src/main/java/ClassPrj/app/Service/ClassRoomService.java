package ClassPrj.app.Service;

import ClassPrj.app.domain.ClassRoom;

public interface ClassRoomService {
	public ClassRoom create(String name,String creator);
	public void join(Long classRoomId,Long userId);
	public void leave(Long classRoomId,Long userId);
}
