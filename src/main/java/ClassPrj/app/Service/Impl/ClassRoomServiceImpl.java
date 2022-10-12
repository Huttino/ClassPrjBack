package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.MemberRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.ClassRoomService;
import ClassPrj.app.domain.ClassRoom;
import ClassPrj.app.domain.Member;
import ClassPrj.app.domain.Student;
import ClassPrj.app.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    private final TeacherRepository teacherRepository;
    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, MemberRepository memberRepository) {
        this.classRoomRepository = classRoomRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ClassRoom create(String name, String creator) {
        ClassRoom toSave = new ClassRoom(name);
        Optional<Teacher> optional = this.teacherRepository.findByUsername(creator);
        if (optional.isPresent()) toSave.setCreator(optional.get());
        else throw new ApiException("Teacher not Found", HttpStatus.NOT_FOUND.value());
        return this.classRoomRepository.save(toSave);
    }

    @Override
    public void join(Long classRoomId, Long userId) {
        Optional<ClassRoom> optionalWhere = classRoomRepository.findById(classRoomId);
        if (optionalWhere.isEmpty()) throw new ApiException("ClassRoom not found", HttpStatus.NOT_FOUND.value());
        ClassRoom where = optionalWhere.get();
        Optional<Student> optionalWho = studentRepository.findById(userId);
        if (optionalWho.isEmpty()) throw new ApiException("Student not found", HttpStatus.NOT_FOUND.value());
        Student who = optionalWho.get();
        Member toSave = new Member();
        toSave.setStudent(who);
        toSave.setClassRoom(where);
        memberRepository.save(toSave);
    }

    @Override
    public void leave(Long classRoomId, Long userId) {
        Optional<Member> toDelete = memberRepository.findMemberByClassRoomIdAndStudentId(classRoomId, userId);
        if (toDelete.isEmpty())
            throw new ApiException("Relation ClassRoom Student not found", HttpStatus.NOT_FOUND.value());
        memberRepository.delete(toDelete.get());
    }

    @Override
    public void delete(Long classRoomId, Long teacherId) {
        Optional<ClassRoom> toDelete = classRoomRepository.findById(classRoomId);
        if (toDelete.isEmpty()) throw new ApiException("ClassRoom not found", HttpStatus.NOT_FOUND.value());
        if (!toDelete.get().getCreator().getId().equals(teacherId)) {
            throw new ApiException("You can't delete a Classroom you didn't create", HttpStatus.UNAUTHORIZED.value());
        } else if (toDelete.get().getMembers() != null && toDelete.get().getMembers().size() > 0) {
            throw new ApiException("you can only delete empty classrooms", HttpStatus.BAD_REQUEST.value());
        }
        classRoomRepository.delete(toDelete.get());
    }

    @Override
    public ClassRoomDTO getClassById(Long id, Long userId) {
        Optional<ClassRoom> optionalToAdapt = this.classRoomRepository.findById(id);
        if (optionalToAdapt.isEmpty()) throw new ApiException("ClassRoom not found", HttpStatus.NOT_FOUND.value());
        ClassRoomDTO toReturn;
        if(this.teacherRepository.existsById(userId)){
            toReturn=ClassRoomMapper.entityToDtoTeacher(optionalToAdapt.get());
        }
        else toReturn=ClassRoomMapper.entityToDto(optionalToAdapt.get(),memberRepository.existsMembersByClassRoomIdAndGradeIsNull(id));
        return toReturn;
    }

    @Override
    public List<ClassRoomDTO> getAllClass() {
        List<ClassRoomDTO> toReturn = new ArrayList<>();
        List<ClassRoom> classRoomList = this.classRoomRepository.findAll();
        if (!classRoomList.isEmpty()) {
            classRoomList.forEach(x -> toReturn.add(ClassRoomMapper.entityToDto(x, !memberRepository.existsMembersByClassRoomIdAndGradeIsNull(x.getId()))));
        }
        return toReturn;
    }

    @Override
    public List<ClassRoomDTO> getClassesFrom(Long teacherId) {
        List<ClassRoomDTO> toReturn = new ArrayList<>();
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        teacher.ifPresentOrElse(x ->
            x.getHasCreated().forEach(y ->
                toReturn.add(ClassRoomMapper.entityToDtoTeacher(y))
            )
        , () -> {
            throw new ApiException("Teacher not found", HttpStatus.NOT_FOUND.value());
        });
        return toReturn;
    }

}

