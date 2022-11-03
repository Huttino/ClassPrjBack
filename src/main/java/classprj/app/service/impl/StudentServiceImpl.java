package classprj.app.service.impl;

import classprj.app.exception.ApiException;
import classprj.app.mapper.ClassRoomMapper;
import classprj.app.mapper.StudentMapper;
import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.model.dto.StudentDTO;
import classprj.app.repository.MemberRepository;
import classprj.app.repository.StudentRepository;
import classprj.app.service.StudentService;
import classprj.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository,
            MemberRepository memberRepository
    ){
        this.studentRepository=studentRepository;
        this.memberRepository=memberRepository;
    }

    public StudentDTO getStudent(Long id){
        Optional<Student> toAdapt=this.studentRepository.findById(id);
        if(toAdapt.isEmpty())throw new ApiException("Student not found", HttpStatus.NOT_FOUND.value());
        return StudentMapper.EntityToDTO(toAdapt.get());
    }
    //TODO recommendation for the user
    @Override
    public List<PublicClassRoomDTO> getRecommendation(Long myId) {
        Optional<Student> studentOptional=this.studentRepository.findById(myId);
        if(studentOptional.isEmpty()) throw new ApiException("Student not found",404);
        Student student=studentOptional.get();
        Map<Long,Integer> scopesValues=new HashMap<>();
        student.getClassList().forEach(
                x->{
                    x.getClassRoom().getScopes().forEach(
                            y->{
                                if(scopesValues.containsKey(y.getId())) scopesValues.put(y.getId(),scopesValues.get(y.getId())+1);
                                else scopesValues.put(y.getId(),1);
                            }
                    );
                }
        );
        scopesValues.forEach(
                (x,y)->{
                    x.longValue();
                }
        );
        return null;
    }

    @Override
    public List<ClassRoomDTO> getClasses(Long myId) {
        List<ClassRoomDTO> toReturn=new ArrayList<>();
        Optional<Student> toGetClasses=this.studentRepository.findById(myId);
        if(toGetClasses.isEmpty())throw new ApiException("Student not found",HttpStatus.NOT_FOUND.value());
        toGetClasses.get().getClassList().forEach(x-> toReturn.add(ClassRoomMapper.entityToDto(x.getClassRoom())));
        return toReturn;
    }
}

