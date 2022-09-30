package ClassPrj.app.Service.Impl;

import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.domain.Student;
import ClassPrj.app.domain.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import ClassPrj.app.Exception.ApiException;
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

	@Override
	public void delete(Long classRoomId,Long teacherId) {
		ClassRoom toDelete=classRoomRepository.findById(classRoomId).get();
		if(toDelete.getCreator().getId().equals(teacherId)) {
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
		boolean found=false;
		if(toAdapt.getCreator().getId()!=userId){
			for(int i=0;i<toAdapt.getMembers().size();i++){
				if(toAdapt.getMembers().get(i).getId()==userId){
					found=true;
				}
			}
		}
		else{
			found=true;
		}
		if(found){
			return ClassRoomMapper.entityToDto(toAdapt);
		}
		else {
			throw new ApiException("You don't have access to this class");
		}
	}
}
