package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Model.Request.RemoveFromClassRequest;
import ClassPrj.app.Model.Request.UpdateGradeRequest;
import ClassPrj.app.Repository.MemberRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.TeacherService;
import ClassPrj.app.domain.Member;
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
	
	@Autowired
	public TeacherServiceImpl(
			TeacherRepository teacherRepository,
			MemberRepository memberRepository
			) {
		this.teacherRepository=teacherRepository;
		this.memberRepository=memberRepository;
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
			if(x.getClassRoom().getCreator().getId().equals(teacherId))
				throw new ApiException("You didn't create this ClassRoom",HttpStatus.UNAUTHORIZED.value());
			else{
				x.setGrade(updateGradeRequest.getGrade());
			}
		});
		this.memberRepository.save(toAssignGrade.get());
    }

	public void removeFromClass(RemoveFromClassRequest request,Long teacherId){
		Optional<Member> where=this.memberRepository.findMemberByClassRoomIdAndStudentId(request.getClassRoomId(), request.getStudentId());
		if(where.isEmpty())throw new ApiException("Student not member of this class",HttpStatus.BAD_REQUEST.value());
		where.ifPresent(x->{
			if(!x.getClassRoom().getCreator().getId().equals(teacherId))
				throw new ApiException("You didn't create this ClassRoom",HttpStatus.UNAUTHORIZED.value());
			else
				this.memberRepository.delete(x);
		});
	}
}
