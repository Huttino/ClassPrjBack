package ClassPrj.app.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ClassPrj.app.security.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Request.NewClassRoomRequest;
import ClassPrj.app.Service.Impl.ClassRoomServiceImpl;
import ClassPrj.app.Service.Impl.TeacherServiceImpl;
import ClassPrj.app.security.PrincipalUtils;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/class")
@Secured("TEACHER")
public class ClassRoomController {
	
	private final ClassRoomServiceImpl classRoomServiceImpl;
	private final TeacherServiceImpl teacherServiceImpl;
	
	@Autowired
	public ClassRoomController(ClassRoomServiceImpl classRoomServiceImpl,TeacherServiceImpl teacherServiceImpl) {
		this.classRoomServiceImpl=classRoomServiceImpl;
		this.teacherServiceImpl=teacherServiceImpl;
	}
	
	@PostMapping("")
	public ResponseEntity<ClassRoomDTO> Create(@Valid @RequestBody NewClassRoomRequest nc){
		String username =PrincipalUtils.extractPrincipal( SecurityContextHolder.getContext().getAuthentication());
		return ResponseEntity.ok().body(ClassRoomMapper.entityToDto(this.classRoomServiceImpl.create(nc.getClassname(),username)));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id")Long id){
		Long creatorId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.classRoomServiceImpl.delete(id,creatorId);
		return ResponseEntity.ok().build();
	}



}
