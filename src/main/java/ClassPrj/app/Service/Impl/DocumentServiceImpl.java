package ClassPrj.app.Service.Impl;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClassPrj.app.Exception.ApiException;
import ClassPrj.app.Mapper.DocumentMapper;

import ClassPrj.app.Model.Dto.DocumentDTO;

import ClassPrj.app.Model.Request.UploadDocumentWithData;
import ClassPrj.app.Repository.ClassRoomRepository;
import ClassPrj.app.Repository.DocumentRepository;
import ClassPrj.app.Repository.StudentRepository;
import ClassPrj.app.Repository.TeacherRepository;
import ClassPrj.app.Service.DocumentService;
import ClassPrj.app.domain.Document;

import ClassPrj.app.domain.Student;


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
	public DocumentDTO upload(UploadDocumentWithData toUpload,Long classId,String username) {
		Document toSave=new Document();
		try {
			toSave = DocumentMapper.RequestToEntity(toUpload);
		} catch (IOException e) {
			throw new ApiException("Erorr during the upload");
		}
		toSave.setUploadedBy(this.teacherRepository.findByUsername(username).get());
		toSave.setUploadedTo(this.classRoomRepository.findById(classId).get());
		DocumentDTO toReturn= DocumentMapper.entityToDTO( this.documentRepository.save(toSave));
		return toReturn;	
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
	public byte[] getFile(Long id,String username) {
		Student asker=this.studentRepository.findByUsername(username).get();
		Document toReturn =this.documentRepository.findById(id).get();
		if (asker.getSubscribedTo().contains(toReturn.getUploadedTo())){
			return toReturn.getFile();
		}
		else throw new ApiException("You don't have access to this resource");
	}
	
}
