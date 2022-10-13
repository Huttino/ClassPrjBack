package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.StudentInClass;
import ClassPrj.app.Model.Request.AddStudentRequest;
import ClassPrj.app.Model.Request.StudentClassRequest;
import ClassPrj.app.Model.Request.UpdateGradeRequest;
import ClassPrj.app.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
	 Optional<Teacher> findByUsername(String username);
	 Optional<Teacher> findById(Long id);
	 Optional<List<Teacher> > findAll();
	 void update(Teacher teacher);
	 void assignGrade(Long teacherId,Long classId, UpdateGradeRequest updateGradeRequest);
	 void removeFromClass(StudentClassRequest request, Long teacherId);

	StudentInClass addToClass(AddStudentRequest addToClass, Long myId);
}
