package ClassPrj.app.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import ClassPrj.app.Exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;
import ClassPrj.app.Service.DocumentService;
import ClassPrj.app.Service.Impl.DocumentServiceImpl;
import ClassPrj.app.security.PrincipalUtils;

@CrossOrigin(value="*",maxAge = 3600)
@RequestMapping("/api/document")
@RestController
public class DocumentController {
	
	private DocumentServiceImpl documentServiceImpl;

	@Autowired
	public DocumentController(DocumentServiceImpl documentServiceImpl) {
		super();
		this.documentServiceImpl = documentServiceImpl;
	}

	@PostMapping("/{id}")
	@Secured("TEACHER")
	public ResponseEntity<?> upload(@ModelAttribute UploadDocumentWithData toUpload,@PathVariable ("id")Long classId){
		String username =PrincipalUtils.extractPrincipal( SecurityContextHolder.getContext().getAuthentication());
		DocumentDTO toReturn= this.documentServiceImpl.upload(toUpload,classId,username);
		return ResponseEntity.ok(toReturn);
	}
	
	@DeleteMapping("/{id}")
	@Secured("TEACHER")
	public ResponseEntity<?> delete(@PathVariable("id") Long documentId){
		String username=PrincipalUtils.extractPrincipal(SecurityContextHolder.getContext().getAuthentication());
		try {
			this.documentServiceImpl.delete(documentId,username);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	@Secured("STUDENT")
	public void download(@PathVariable("id")Long documentId,HttpServletResponse response){
		String username=PrincipalUtils.extractPrincipal(SecurityContextHolder.getContext().getAuthentication());
		try {
			response.getOutputStream().write(this.documentServiceImpl.getFile(documentId,username));
			response.flushBuffer();
		} catch (IOException e) {
			throw new ApiException("IOError writing file to output stream");
		}
		
	}
}
