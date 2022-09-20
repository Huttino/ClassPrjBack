package ClassPrj.app.Controller;


import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Model.Request.UpdatePasswordRequest;
import ClassPrj.app.Model.Request.UpdateUserRequest;
import ClassPrj.app.Service.Impl.ClassRoomServiceImpl;
import ClassPrj.app.Service.Impl.UserServiceImpl;
import ClassPrj.app.Service.UserService;
import ClassPrj.app.security.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import javax.validation.Valid;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private ClassRoomServiceImpl classRoomServiceImpl;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(ClassRoomServiceImpl classRoomServiceImpl, UserServiceImpl userServiceImpl) {
        this.classRoomServiceImpl = classRoomServiceImpl;
        this.userServiceImpl=userServiceImpl;
    }
    //toTest
    @PutMapping("/classRoom/{id}")
    @Secured("STUDENT")
    public ResponseEntity<?> join(@PathVariable(name="id") Long classRoomId){
        Long userId = PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomServiceImpl.join(classRoomId,userId);
        return ResponseEntity.ok().build();
    }
    //toTest
    @DeleteMapping("/classRoom/{id}")
    @Secured("STUDENT")
    public ResponseEntity<?> leave(@PathVariable(name="id") Long classRoomId){
        Long userId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
        this.classRoomServiceImpl.leave(classRoomId,userId);
        return ResponseEntity.ok().build();
    }


    //toTest
    @PutMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){

        this.userServiceImpl.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest){
    	this.userServiceImpl.updateUser(updateUserRequest);
    	return ResponseEntity.ok().build();
    }
}
