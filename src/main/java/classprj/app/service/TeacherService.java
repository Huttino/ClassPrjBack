package classprj.app.service;

import classprj.app.model.dto.StudentInClass;
import classprj.app.model.request.AddStudentRequest;
import classprj.app.model.request.StudentClassRequest;
import classprj.app.model.request.UpdateGradeRequest;
import classprj.app.domain.Teacher;

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
