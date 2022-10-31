package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Member;
import classprj.app.domain.Teacher;
import classprj.app.exception.ApiException;
import classprj.app.mapper.ClassRoomMapper;
import classprj.app.mapper.FileSaver;
import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.ClassRoomStrippedDTO;
import classprj.app.model.request.UpdateCoverRequest;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.MemberRepository;
import classprj.app.repository.StudentRepository;
import classprj.app.repository.TeacherRepository;
import classprj.app.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ClassRoomDTO create(String name, Long teacherId) {
        ClassRoom toSave = new ClassRoom(name);
        Optional<Teacher> teacher = this.teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) throw new ApiException("Teacher not Found", HttpStatus.NOT_FOUND.value());
        else {
            toSave.setCreator(teacher.get());
            ClassRoomDTO classRoomDTO=ClassRoomMapper.entityToDtoTeacher(classRoomRepository.save(toSave));
            try {
                FileSaver.createDirs(classRoomDTO.getId());
            } catch (IOException e) {
                throw new ApiException("couldn't create the classRoom Data",500);
            }
            return classRoomDTO;
        }
    }

    @Override
    public void join(Long classRoomId, Long userId) {
        classRoomRepository.findById(classRoomId).ifPresentOrElse(
                x -> studentRepository.findById(userId).ifPresentOrElse(
                        y -> {
                            Member toSave = new Member(0L, y, x);
                            this.memberRepository.save(toSave);
                        }, () -> {
                            throw new ApiException("Student not found", HttpStatus.NOT_FOUND.value());
                        }
                ), () -> {
                    throw new ApiException("ClassRoom not found", HttpStatus.NOT_FOUND.value());
                }
        );
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
        if (this.teacherRepository.existsById(userId)) {
            toReturn = ClassRoomMapper.entityToDtoTeacher(optionalToAdapt.get());
        } else
            toReturn = ClassRoomMapper.entityToDto(optionalToAdapt.get());
        return toReturn;
    }

    @Override
    public List<ClassRoomStrippedDTO> getAllClass() {
        List<ClassRoomStrippedDTO> toReturn = new ArrayList<>();
        List<ClassRoom> classRoomList = this.classRoomRepository.findAll();
        if (!classRoomList.isEmpty()) {
            classRoomList.forEach(x -> toReturn.add(ClassRoomMapper.entityToStrippedDto(x, !memberRepository.existsMembersByClassRoomIdAndGradeIsNull(x.getId()))));
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

    @Override
    public void updateCover(UpdateCoverRequest request, Long classId, Long teacherId) {
        if (request.getCover().isEmpty()) {
            throw new ApiException("can't update the cover,provided File is empty", HttpStatus.BAD_REQUEST.value());
        }
        MultipartFile toSave;
        toSave = request.getCover();
        Optional<ClassRoom> optionalClassRoom = this.classRoomRepository.findById(classId);
        optionalClassRoom.ifPresentOrElse(
                (x) -> {
                    try {
                        x.setPathCover(FileSaver.saveFile(classId, false, toSave));
                    } catch (IOException e) {
                        throw new ApiException(e.toString());
                    }
                    this.classRoomRepository.save(x);
                },
                () -> {
                    throw new ApiException("classRoom not found", 404);
                }
        );
    }

}

