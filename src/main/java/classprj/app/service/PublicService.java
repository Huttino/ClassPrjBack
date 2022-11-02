package classprj.app.service;


import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.model.dto.ScopeDto;

import java.util.List;

public interface PublicService {

    PublicClassRoomDTO getClassRoom(Long classId);

    byte[] getCover(Long classId);

    List<PublicClassRoomDTO> getClassRoomForLanding();

    List<ScopeDto> getAllScopes();
}
