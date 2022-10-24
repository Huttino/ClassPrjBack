package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<ClassRoomDTO> getClasses(Long myId);

    StudentDTO getStudent(Long id);

}
