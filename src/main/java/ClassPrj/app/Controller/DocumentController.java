package ClassPrj.app.Controller;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;
import ClassPrj.app.Service.DocumentService;
import ClassPrj.app.security.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(value="*",maxAge = 3600)
@RequestMapping("/api/document")
@RestController
public class DocumentController {
	
	private final DocumentService documentService;

	@Autowired
	public DocumentController(DocumentService documentService) {
		super();
		this.documentService = documentService;
	}

	@PostMapping("/{id}")
	@Secured("TEACHER")
	public ResponseEntity<?> upload(@ModelAttribute UploadDocumentWithData toUpload,@PathVariable ("id")Long classId){
		String username =PrincipalUtils.extractPrincipal( SecurityContextHolder.getContext().getAuthentication());
		List<DocumentDTO> toReturn= this.documentService.upload(toUpload,classId,username);
		return ResponseEntity.ok(toReturn);
	}
	
	@DeleteMapping("/{id}")
	@Secured("TEACHER")
	public ResponseEntity<?> delete(@PathVariable("id") Long documentId){
		String username=PrincipalUtils.extractPrincipal(SecurityContextHolder.getContext().getAuthentication());
		try {
			this.documentService.delete(documentId,username);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	@Secured({"STUDENT","TEACHER"})
	public void download(@PathVariable("id")Long documentId,HttpServletResponse response){
		Long userid=PrincipalUtils.loggerUserIdFromContext(SecurityContextHolder.getContext());
		try {
			response.getOutputStream().write(this.documentService.getFile(documentId,userid));
			response.flushBuffer();
		} catch (IOException e) {
			throw new ApiException("IOError writing file to output stream");
		}
		
	}
}
