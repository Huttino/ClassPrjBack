package classprj.app.controller;

import classprj.app.model.dto.ClassRoomDTO;
import classprj.app.model.dto.StudentInClass;
import classprj.app.model.dto.VideoLessonDTO;
import classprj.app.model.request.*;
import classprj.app.security.PrincipalUtils;
import classprj.app.service.ClassRoomService;
import classprj.app.service.TeacherService;
import classprj.app.service.VideoLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private final VideoLessonService videoLessonService;

	
	@Autowired
	public ClassRoomController(
			ClassRoomService classRoomService,
			TeacherService teacherService,
			VideoLessonService videoLessonService
	) {
		this.classRoomService=classRoomService;
		this.teacherService=teacherService;
		this.videoLessonService=videoLessonService;

	}

	@Secured("TEACHER")
	@PostMapping("")
	public ResponseEntity<ClassRoomDTO> Create(@Valid @RequestBody NewClassRoomRequest nc){
		Long teacherId =PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		return ResponseEntity.ok().body(this.classRoomService.create(nc.getClassname(),teacherId));
	}

	@Secured("TEACHER")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(name="id")Long id){
		Long creatorId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.classRoomService.delete(id,creatorId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
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
	public ResponseEntity removeFromClass(@RequestBody StudentClassRequest removeFromClassRequest){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.teacherService.removeFromClass(removeFromClassRequest,myId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@PutMapping("")
	@Secured("TEACHER")
	public ResponseEntity<StudentInClass> addToClass(@RequestBody AddStudentRequest addToClass){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());

		return ResponseEntity.ok(this.teacherService.addToClass(addToClass,myId));
	}

	@PostMapping("/{id}/lesson")
	@Secured("TEACHER")
	public ResponseEntity<VideoLessonDTO> addLesson(@PathVariable(name="id")Long classId,@RequestBody newVideoLessonRequest request){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		return ResponseEntity.ok(this.videoLessonService.addLesson(request,classId,myId));
	}

}
