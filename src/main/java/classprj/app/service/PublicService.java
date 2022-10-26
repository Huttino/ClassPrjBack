package classprj.app.service;


import classprj.app.model.dto.PublicClassRoomDTO;

public interface PublicService {

    PublicClassRoomDTO getClassRoom(Long classId);

    byte[] getCover(Long classId);
}
