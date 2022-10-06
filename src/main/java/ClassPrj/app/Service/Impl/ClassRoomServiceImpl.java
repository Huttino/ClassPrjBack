package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.ClassRoomService;
import ClassPrj.app.domain.ClassRoom;
import ClassPrj.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
		toAdd.getSubscribedTo().add(toSave);
		studentRepository.save(toAdd);
	}

	@Override
	public void leave(Long classRoomId,Long userId){
		ClassRoom toSave = classRoomRepository.findById(classRoomId).get();
		Student toRemove= studentRepository.findById(userId).get();
		toSave.getMembers().remove(toRemove);
		toRemove.getSubscribedTo().remove(toSave);
		this.classRoomRepository.save(toSave);
	}

	@Override
	public void delete(Long classRoomId,Long teacherId) {
		ClassRoom toDelete=classRoomRepository.findById(classRoomId).get();
		if(!toDelete.getCreator().getId().equals(teacherId)) {
			throw new ApiException("You can't delete a Classroom you didn't create");
		}
		else if(toDelete.getMembers()!=null && toDelete.getMembers().size()>0) {
			throw new ApiException("you can only delete empty classrooms");
		}
		classRoomRepository.delete(toDelete);
	}

	@Override
	public ClassRoomDTO getClassById(Long id,Long userId){
		ClassRoom toAdapt=this.classRoomRepository.findById(id).get();
		return ClassRoomMapper.entityToDto(toAdapt);
	}

	@Override
	public List<ClassRoomDTO> getAllClass() {
		List<ClassRoomDTO> toReturn=new ArrayList();
		List<ClassRoom> classRoomList=this.classRoomRepository.findAll();
		if(classRoomList!=null&&!classRoomList.isEmpty())
		classRoomList.forEach(
				x->{
					toReturn.add(ClassRoomMapper.entityToDto(x));
				}
		);
		return toReturn;
	}
}
