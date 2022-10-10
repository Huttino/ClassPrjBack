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
import ClassPrj.app.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<Teacher> optional=this.teacherRepository.findByUsername(creator);
		if(optional.isPresent())
		toSave.setCreator(optional.get());
		else throw new ApiException("Teacher not Found");
		return this.classRoomRepository.save(toSave);
	}

	@Override
	public void join(Long classRoomId,Long userId){
		Optional<ClassRoom> optionalToSave = classRoomRepository.findById(classRoomId);
		if (optionalToSave.isEmpty()) throw new ApiException("ClassRoom not found");
		ClassRoom toSave = optionalToSave.get();
		Optional<Student> optionalToAdd=studentRepository.findById(userId);
		if(optionalToAdd.isEmpty()) throw new ApiException("Student not found");
		Student toAdd= optionalToAdd.get();
		toSave.getMembers().add(toAdd);
		toAdd.getSubscribedTo().add(toSave);
		studentRepository.save(toAdd);
	}

	@Override
	public void leave(Long classRoomId,Long userId) {
		Optional<ClassRoom> optionalToSave = classRoomRepository.findById(classRoomId);
		if (optionalToSave.isEmpty()) throw new ApiException("ClassRoom not found");
		ClassRoom toSave = optionalToSave.get();
		Optional<Student> optionalToRemove=studentRepository.findById(userId);
		if(optionalToRemove.isEmpty()) throw new ApiException("Student not found");
		Student toRemove= optionalToRemove.get();
		toSave.getMembers().remove(toRemove);
		toRemove.getSubscribedTo().remove(toSave);
		this.classRoomRepository.save(toSave);
	}

	@Override
	public void delete(Long classRoomId,Long teacherId) {
		Optional<ClassRoom> toDelete=classRoomRepository.findById(classRoomId);
		if(toDelete.isEmpty()) throw new ApiException("ClassRoom not found");
		if(!toDelete.get().getCreator().getId().equals(teacherId)) {
			throw new ApiException("You can't delete a Classroom you didn't create");
		}
		else if(toDelete.get().getMembers()!=null && toDelete.get().getMembers().size()>0) {
			throw new ApiException("you can only delete empty classrooms");
		}
		classRoomRepository.delete(toDelete.get());
	}

	@Override
	public ClassRoomDTO getClassById(Long id,Long userId){
		Optional<ClassRoom> optionalToAdapt=this.classRoomRepository.findById(id);
		if(optionalToAdapt.isEmpty()) throw new ApiException("ClassRoom not found");
		return ClassRoomMapper.entityToDto(optionalToAdapt.get());
	}

	@Override
	public List<ClassRoomDTO> getAllClass() {
		List<ClassRoomDTO> toReturn=new ArrayList<>();
		List<ClassRoom> classRoomList=this.classRoomRepository.findAll();
		if(!classRoomList.isEmpty()) {
			classRoomList.forEach(
					x -> toReturn.add(ClassRoomMapper.entityToDto(x))
			);
		}
		return toReturn;
	}
}
