package classprj.app.service;

import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<PublicClassRoomDTO> getRecommendation(Long myId);


    List<ClassRoomDTO> getClasses(Long myId);

    StudentDTO getStudent(Long id);

}
