package classprj.app.controller;

import classprj.app.exception.ApiException;
import classprj.app.model.dto.*;
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
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
		return ResponseEntity.ok().body(this.classRoomService.create(nc,teacherId));
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
	public ResponseEntity<List<ClassRoomStrippedDTO>> getAllClass(){
		List<ClassRoomStrippedDTO> toReturn=this.classRoomService.getAllClass();
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
	public ResponseEntity<VideoLessonDTO> addLesson(@PathVariable(name="id")Long classId,@RequestBody @Valid newVideoLessonRequest request){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		return ResponseEntity.ok(this.videoLessonService.addLesson(request,classId,myId));
	}

	@DeleteMapping("/{classId}/lesson/{lessonId}")
	@Secured("TEACHER")
	public ResponseEntity removeLesson(@PathVariable(name="classId")Long classId,@PathVariable(name="lessonId")Long lessonId){
		Long myId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		this.videoLessonService.remove(myId,classId,lessonId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{id}/cover")
	@Secured("TEACHER")
	public ResponseEntity updateCover(@ModelAttribute UpdateCoverRequest request,@PathVariable(name="id")Long classId){
		Long teacherId=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		try {
			this.classRoomService.updateCover(request,classId,teacherId);
		} catch (IOException e) {
			throw new ApiException("couldn't update Cover",500);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/recommendation/{id}")
	@Secured("STUDENT")
	public ResponseEntity<Set<PublicClassRoomDTO>> findByScopes(@PathVariable(name = "id") Long classId){
		return ResponseEntity.ok( this.classRoomService.findByScopes(classId));
	}

}
