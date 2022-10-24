package ClassPrj.app.Controller;


import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.TeacherMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.ROLEVALUE;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;
import ClassPrj.app.Model.Request.UpdateUserRequest;
import ClassPrj.app.Service.ClassRoomService;
import ClassPrj.app.Service.StudentService;
import ClassPrj.app.Service.TeacherService;
import ClassPrj.app.Service.UserService;
import ClassPrj.app.domain.Teacher;
import ClassPrj.app.security.PrincipalUtils;
import ClassPrj.app.security.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/me")
public class UserController {

    private final ClassRoomService classRoomService;
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public UserController(ClassRoomService classRoomService, UserService userService,StudentService studentService,TeacherService teacherService) {
        this.classRoomService = classRoomService;
        this.userService=userService;
        this.studentService=studentService;
        this.teacherService=teacherService;
    }

    @PutMapping("/classRoom/{id}")
    @Secured("STUDENT")
    public ResponseEntity<?> join(@PathVariable(name="id") Long classRoomId){
        Long userId = PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomService.join(classRoomId,userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/classRoom/{id}")
    @Secured("STUDENT")
    public ResponseEntity<?> leave(@PathVariable(name="id") Long classRoomId){
        Long userId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomService.leave(classRoomId,userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){

        this.userService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest){
    	this.userService.updateUser(updateUserRequest);
    	return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getMe(){
        UserDetailImpl detailImpl= PrincipalUtils.extractPrincipalObject(SecurityContextHolder.getContext().getAuthentication());
        if (detailImpl.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals(ROLEVALUE.STUDENT.getRoleName()))){
            return ResponseEntity.ok(studentService.getStudent(detailImpl.getId()));
        }
        else if(detailImpl.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals(ROLEVALUE.TEACHER.getRoleName()))){
            Optional<Teacher> toReturn= teacherService.findById(detailImpl.getId());
            if(toReturn.isEmpty())throw new ApiException("Teacher not found");
            return ResponseEntity.ok(TeacherMapper.EntityToDTO(toReturn.get()));
        }
        else throw new ApiException("Error with your Registration");
    }

    @GetMapping("/classRoom")
    @Secured("STUDENT")
    public ResponseEntity<List<ClassRoomDTO>> getMyClasses(){
        Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        List<ClassRoomDTO> toReturn= this.studentService.getClasses(myId);
        return ResponseEntity.ok(toReturn);
    }

}
