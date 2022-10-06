package ClassPrj.app.Controller;

import ClassPrj.app.Mapper.ClassRoomMapper;
import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Request.NewClassRoomRequest;
import ClassPrj.app.Service.Impl.ClassRoomServiceImpl;
import ClassPrj.app.Service.Impl.TeacherServiceImpl;
import ClassPrj.app.security.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/class")

public class ClassRoomController {
	
	private final ClassRoomServiceImpl classRoomServiceImpl;
	private final TeacherServiceImpl teacherServiceImpl;
	
	@Autowired
	public ClassRoomController(ClassRoomServiceImpl classRoomServiceImpl,TeacherServiceImpl teacherServiceImpl) {
		this.classRoomServiceImpl=classRoomServiceImpl;
		this.teacherServiceImpl=teacherServiceImpl;
	}

	@Secured("TEACHER")
	@PostMapping("")
	public ResponseEntity<ClassRoomDTO> Create(@Valid @RequestBody NewClassRoomRequest nc){
		String username =PrincipalUtils.extractPrincipal( SecurityContextHolder.getContext().getAuthentication());
		return ResponseEntity.ok().body(ClassRoomMapper.entityToDto(this.classRoomServiceImpl.create(nc.getClassname(),username)));
	}

	@Secured("TEACHER")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id")Long id){
		Long creatorId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.classRoomServiceImpl.delete(id,creatorId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassRoomDTO> getClass(@PathVariable(name="id")Long id){
		Long userId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		ClassRoomDTO toReturn=this.classRoomServiceImpl.getClassById(id,userId);
		return ResponseEntity.ok(toReturn);
	}

	@GetMapping("")
	public ResponseEntity<List<ClassRoomDTO>> getAllClass(){
		List<ClassRoomDTO> toReturn=this.classRoomServiceImpl.getAllClass();
		return ResponseEntity.ok(toReturn);
	}



}
