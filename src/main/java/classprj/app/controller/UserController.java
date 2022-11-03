package classprj.app.controller;


import classprj.app.exception.ApiException;
import classprj.app.mapper.TeacherMapper;
import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.model.dto.UserDTO;
import classprj.app.model.ROLEVALUE;
import classprj.app.model.request.UpdatePasswordRequest;
import classprj.app.model.request.UpdateUserRequest;
import classprj.app.service.ClassRoomService;
import classprj.app.service.StudentService;
import classprj.app.service.TeacherService;
import classprj.app.service.UserService;
import classprj.app.domain.Teacher;
import classprj.app.security.PrincipalUtils;
import classprj.app.security.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity join(@PathVariable(name="id") Long classRoomId){
        Long userId = PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomService.join(classRoomId,userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/classRoom/{id}")
    @Secured("STUDENT")
    public ResponseEntity leave(@PathVariable(name="id") Long classRoomId){
        Long userId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomService.leave(classRoomId,userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/password")
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){

        this.userService.updatePassword(updatePasswordRequest);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody UpdateUserRequest updateUserRequest){
    	this.userService.updateUser(updateUserRequest);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<? extends UserDTO> getMe(){
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

    @GetMapping("/recommendation")
    @Secured("STUDENT")
    public ResponseEntity<List<PublicClassRoomDTO>> getRecommendation(){
        Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        List<PublicClassRoomDTO> toReturn=studentService.getRecommendation(myId);
        return ResponseEntity.ok(toReturn);
    }

}
