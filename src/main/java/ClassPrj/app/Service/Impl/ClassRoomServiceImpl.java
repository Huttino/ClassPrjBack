package ClassPrj.app.Service.Impl;

import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.ClassRoomService;
import ClassPrj.app.domain.ClassRoom;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

	private final TeacherRepository teacherRepository;
	private final ClassRoomRepository classRoomRepository;
	private final StudentRepository studentRepository;
	
	@Autowired
	public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository,TeacherRepository teacherRepository,StudentRepository studentRepository) {
		this.classRoomRepository=classRoomRepository;
		this.teacherRepository=teacherRepository;
		this.studentRepository=studentRepository;
	}
	
	@Override
	public ClassRoom create(String name,String creator) {
		ClassRoom toSave=new ClassRoom(name);
		toSave.setCreator(this.teacherRepository.findByUsername(creator).get());
		ClassRoom toReturn= this.classRoomRepository.save(toSave);
		return toReturn;
	}

	@Override
	public void join(Long classRoomId,Long userId){
		ClassRoom toSave = classRoomRepository.findById(classRoomId).get();
		Student toAdd= studentRepository.findById(userId).get();
		toSave.getMembers().add(toAdd);
		classRoomRepository.save(toSave);
	}

	@Override
	public void leave(Long classRoomId,Long userId){
		ClassRoom toSave = classRoomRepository.findById(classRoomId).get();
		Student toAdd= studentRepository.findById(userId).get();
		toSave.getMembers().remove(toAdd);
		this.classRoomRepository.save(toSave);
	}

}
