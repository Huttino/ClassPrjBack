package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.StudentMapper;
import ClassPrj.app.Model.Dto.StudentInClass;
import ClassPrj.app.Model.Request.AddStudentRequest;
import ClassPrj.app.Model.Request.StudentClassRequest;
import ClassPrj.app.Model.Request.UpdateGradeRequest;
import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.MemberRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.TeacherService;
import ClassPrj.app.domain.ClassRoom;
import ClassPrj.app.domain.Member;
import ClassPrj.app.domain.Student;
import ClassPrj.app.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeacherServiceImpl implements TeacherService{
	private final TeacherRepository teacherRepository;
	private final MemberRepository memberRepository;
	private final StudentRepository studentRepository;
	private final ClassRoomRepository classRoomRepository;
	
	@Autowired
	public TeacherServiceImpl(
			TeacherRepository teacherRepository,
			MemberRepository memberRepository,
			StudentRepository studentRepository,
			ClassRoomRepository classRoomRepository
			) {
		this.teacherRepository=teacherRepository;
		this.memberRepository=memberRepository;
		this.studentRepository=studentRepository;
		this.classRoomRepository=classRoomRepository;
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

	@Override
    public void assignGrade(Long teacherId,Long classId, UpdateGradeRequest updateGradeRequest) {
		Optional<Member> toAssignGrade=this.memberRepository.findMemberByClassRoomIdAndStudentId(classId, updateGradeRequest.getStudentId());
		if(toAssignGrade.isEmpty())throw new ApiException("Student not member of this class", HttpStatus.BAD_REQUEST.value());
		toAssignGrade.ifPresent(x->{
			if(!x.getClassRoom().getCreator().getId().equals(teacherId))
				throw new ApiException("You didn't create this ClassRoom",HttpStatus.UNAUTHORIZED.value());
			else{
				x.setGrade(updateGradeRequest.getGrade());
			}
		});
		this.memberRepository.save(toAssignGrade.get());
    }

	@Override
	public void removeFromClass(StudentClassRequest request, Long teacherId){
		Optional<Member> where=this.memberRepository.findMemberByClassRoomIdAndStudentId(request.getClassRoomId(), request.getStudentId());
		if(where.isEmpty())throw new ApiException("Student not member of this class",HttpStatus.BAD_REQUEST.value());
		where.ifPresent(x->{
			if(!x.getClassRoom().getCreator().getId().equals(teacherId))
				throw new ApiException("You didn't create this ClassRoom",HttpStatus.UNAUTHORIZED.value());
			else
				this.memberRepository.delete(x);
		});
	}

	@Override
	public StudentInClass addToClass(AddStudentRequest addToClass, Long myId) {
		Optional<Student> who= studentRepository.findByUsername(addToClass.getStudentUser());
		if(who.isEmpty())throw new ApiException("Student Not Found",HttpStatus.NOT_FOUND.value());
		Optional<ClassRoom> where=classRoomRepository.findById(addToClass.getClassRoomId());
		where.ifPresentOrElse(
				x->{
					if(!x.getCreator().getId().equals(myId)){
						throw new ApiException("You didn't create this classRoom",HttpStatus.UNAUTHORIZED.value());
					}
					Member toSave=new Member(0L,who.get(),x);
					this.memberRepository.save(toSave);
				},
				()->{throw new ApiException("ClassRoom Not Found",HttpStatus.NOT_FOUND.value());}
		);

		return StudentMapper.EntityToInClass(who.get());
	}
}
