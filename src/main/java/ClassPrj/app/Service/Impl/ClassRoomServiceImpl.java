package ClassPrj.app.Service.Impl;

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
	
	@Autowired
	public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository,TeacherRepository teacherRepository) {
		this.classRoomRepository=classRoomRepository;
		this.teacherRepository=teacherRepository;
	}
	
	@Override
	public ClassRoom create(String name,String creator) {
		ClassRoom toSave=new ClassRoom(name);
		toSave.setCreator(this.teacherRepository.findByUsername(creator).get());
		ClassRoom toReturn= this.classRoomRepository.save(toSave);
		return toReturn;
	}

}
