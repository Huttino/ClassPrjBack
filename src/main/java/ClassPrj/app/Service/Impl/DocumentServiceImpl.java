package ClassPrj.app.Service.Impl;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.DocumentMapper;
import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;
import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.DocumentRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.DocumentService;
import ClassPrj.app.domain.ClassRoom;
import ClassPrj.app.domain.Document;
import ClassPrj.app.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DocumentServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	private final TeacherRepository teacherRepository;
	private final ClassRoomRepository classRoomRepository;
	private final StudentRepository studentRepository;
	
	@Autowired
	public DocumentServiceImpl(DocumentRepository documentRepository, TeacherRepository teacherRepository,
			ClassRoomRepository classRoomRepository,StudentRepository studentRepository) {
		super();
		this.documentRepository = documentRepository;
		this.teacherRepository = teacherRepository;
		this.classRoomRepository = classRoomRepository;
		this.studentRepository=studentRepository;
	}

	@Override
	public List<DocumentDTO> upload(UploadDocumentWithData toUpload,Long classId,String username) {
		List<Document> toSave=DocumentMapper.RequestToEntity(toUpload);
		Teacher uploader=this.teacherRepository.findByUsername(username).get();
		ClassRoom uploadedTo=this.classRoomRepository.findById(classId).get();
		toSave.forEach(x->{
			x.setUploadedBy(uploader);
			x.setUploadedTo(uploadedTo);
		});
		List<DocumentDTO> savedDocuments=new ArrayList<>();
		toSave.forEach(x->{
			savedDocuments.add(DocumentMapper.entityToDTO( this.documentRepository.save(x)));
		});
		return savedDocuments;	
	}

	@Override
	public void delete(Long documentId,String username) throws Exception {
		Document toBeDeleted= this.documentRepository.findById(documentId).get();
		if (toBeDeleted.getUploadedBy().getUsername().equals(username)) {
			this.documentRepository.delete(toBeDeleted);
		}
		else {
			throw new ApiException("Can't delete someone else's files");
		}
	}

	@Override
	public byte[] getFile(Long id,Long userid) {
		Document toReturn =this.documentRepository.findById(id).orElseThrow(()->{
			throw new ApiException("document not Found");
		});
		if(!toReturn.getUploadedBy().getId().equals(userid)){
			if(!toReturn.getUploadedTo().getMembers().stream().anyMatch(x->{
				return x.getId()==userid;
			})){
				throw new ApiException("You don't have access to this document");
			}
		}
		return toReturn.getFile();
	}
	
}
