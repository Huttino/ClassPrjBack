package ClassPrj.app.Service;

import ClassPrj.app.Model.Request.UpdateGradeRequest;
import ClassPrj.app.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
	public Optional<Teacher> findByUsername(String username);
	public Optional<Teacher> findById(Long id);
	public Optional<List<Teacher> > findAll();
	public void update(Teacher teacher);
	public void assignGrade(Long classId, UpdateGradeRequest updateGradeRequest);
	}
