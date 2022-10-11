package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Mapper.StudentMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.StudentDTO;
import ClassPrj.app.Repository.MemberRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Service.StudentService;
import ClassPrj.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ClassRoomDTO> getClasses(Long myId) {
        List<ClassRoomDTO> toReturn=new ArrayList<>();
        Optional<Student> toGetClasses=this.studentRepository.findById(myId);
        if(toGetClasses.isEmpty())throw new ApiException("Student not found",HttpStatus.NOT_FOUND.value());
        toGetClasses.get().getClassList().forEach(x-> toReturn.add(ClassRoomMapper.entityToDto(x.getClassRoom(),memberRepository.existsMembersByClassRoomIdAndGradeIsNotNull(x.getId()))));
        return toReturn;
    }
}

