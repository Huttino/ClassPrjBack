package ClassPrj.app.Service.Impl;

import ClassPrj.app.Mapper.StudentMapper;
import ClassPrj.app.Model.Dto.StudentDTO;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Service.StudentService;
import ClassPrj.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public StudentDTO getStudent(Long id){
        Student toAdapt=this.studentRepository.findById(id).get();
        return StudentMapper.EntityToDTO(toAdapt);
    }
}

