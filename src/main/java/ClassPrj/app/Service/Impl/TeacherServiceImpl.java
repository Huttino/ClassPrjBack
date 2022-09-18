package ClassPrj.app.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.TeacherService;
import ClassPrj.app.domain.Teacher;


@Service
public class TeacherServiceImpl implements TeacherService{
	private final TeacherRepository teacherRepository;
	
	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository=teacherRepository;
	}

	@Override
	public Optional<Teacher> findByUsername(String username) {
		return this.teacherRepository.findByUsername(username);
	}

	@Override
	public Optional<Teacher> findById(Long id) {
		return this.teacherRepository.findById(id);
	}

	@Override
	public Optional<List<Teacher>> findAll() {
		return Optional.of(this.teacherRepository.findAll());
	}

	@Override
	public void update(Teacher teacher) {
		
	}
	

}
