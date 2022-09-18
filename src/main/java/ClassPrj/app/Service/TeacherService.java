package ClassPrj.app.Service;

import java.util.List;
import java.util.Optional;

import ClassPrj.app.domain.Teacher;

public interface TeacherService {
	public Optional<Teacher> findByUsername(String username);
	public Optional<Teacher> findById(Long id);
	public Optional<List<Teacher> > findAll();
	public void update(Teacher teacher);
}
