package ClassPrj.app.Controller;

import ClassPrj.app.Model.Dto.ClassRoomDTO;
import ClassPrj.app.Model.Dto.StudentInClass;
import ClassPrj.app.Model.Request.AddStudentRequest;
import ClassPrj.app.Model.Request.NewClassRoomRequest;
import ClassPrj.app.Model.Request.StudentClassRequest;
import ClassPrj.app.Model.Request.UpdateGradeRequest;
import ClassPrj.app.Service.ClassRoomService;
import ClassPrj.app.Service.TeacherService;
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
	
	private final ClassRoomService classRoomService;
	private final TeacherService teacherService;

	
	@Autowired
	public ClassRoomController(
			ClassRoomService classRoomService,
			TeacherService teacherService
	) {
		this.classRoomService=classRoomService;
		this.teacherService=teacherService;

	}

	@Secured("TEACHER")
	@PostMapping("")
	public ResponseEntity<ClassRoomDTO> Create(@Valid @RequestBody NewClassRoomRequest nc){
		Long teacherId =PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		return ResponseEntity.ok().body(this.classRoomService.create(nc.getClassname(),teacherId));
	}

	@Secured("TEACHER")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id")Long id){
		Long creatorId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.classRoomService.delete(id,creatorId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassRoomDTO> getClass(@PathVariable(name="id")Long id){
		Long userId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		ClassRoomDTO toReturn=this.classRoomService.getClassById(id,userId);
		return ResponseEntity.ok(toReturn);
	}

	@GetMapping("/creator/{id}")
	@Secured("TEACHER")
	public ResponseEntity<List<ClassRoomDTO>> getClassesFrom(@PathVariable(name="id")Long teacherId){
		List<ClassRoomDTO> toReturn=this.classRoomService.getClassesFrom(teacherId);
		return ResponseEntity.ok(toReturn);
	}

	@GetMapping("")
	public ResponseEntity<List<ClassRoomDTO>> getAllClass(){
		List<ClassRoomDTO> toReturn=this.classRoomService.getAllClass();
		return ResponseEntity.ok(toReturn);
	}

	@PatchMapping("/{id}")
	@Secured("TEACHER")
	public ResponseEntity<?> assignGrade(@PathVariable(name="id")Long classId,@RequestBody UpdateGradeRequest updateGradeRequest){
		Long myId =PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.teacherService.assignGrade(myId,classId,updateGradeRequest);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("")
	@Secured("TEACHER")
	public ResponseEntity<?> removeFromClass(@RequestBody StudentClassRequest removeFromClassRequest){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.teacherService.removeFromClass(removeFromClassRequest,myId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("")
	@Secured("TEACHER")
	public ResponseEntity<StudentInClass> addToClass(@RequestBody AddStudentRequest addToClass){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());

		return ResponseEntity.ok(this.teacherService.addToClass(addToClass,myId));
	}

}
