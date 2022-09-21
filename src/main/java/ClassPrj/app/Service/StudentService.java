package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.ClassRoomDTO;

import java.util.List;

public interface StudentService {
    List<ClassRoomDTO> getClasses(Long myId);
}
