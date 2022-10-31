package classprj.app.controller;

import classprj.app.exception.ApiException;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping("/api/public")
public class PublicController {

    private final PublicService publicService;
    @Autowired
    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<PublicClassRoomDTO> classInfo(@PathVariable(name="id")Long classId){
        return ResponseEntity.ok(this.publicService.getClassRoom(classId));
    }
    //TODO INCOMPLETE
    @GetMapping("/class/{id}/cover")
    public void downloadCover(@PathVariable("id")Long classId, HttpServletResponse response){
        try{
            response.getOutputStream().write(this.publicService.getCover(classId));
            response.flushBuffer();
        }catch(IOException e){
            throw new ApiException("IOError writing file to output stream");
        }
    }

    @GetMapping("/class/forLanding")
    public ResponseEntity<List<PublicClassRoomDTO>> classRoomsForLanding(){
        return ResponseEntity.ok(this.publicService.getClassRoomForLanding());
    }
}
