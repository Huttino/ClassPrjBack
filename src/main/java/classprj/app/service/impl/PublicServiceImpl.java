package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.exception.ApiException;
import classprj.app.mapper.ClassRoomMapper;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicServiceImpl implements PublicService {

    private final ClassRoomRepository classRoomRepository;
    @Autowired
    public PublicServiceImpl(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    @Override
    public PublicClassRoomDTO getClassRoom(Long classId) {
        Optional<ClassRoom> optionalToRet=this.classRoomRepository.findById(classId);
        if(optionalToRet.isEmpty()){
            throw new ApiException("ClassRoom not Fount",404);
        }
        return ClassRoomMapper.entityToPublicDTO(optionalToRet.get());
    }

    @Override
    public byte[] getCover(Long classId) {
        Optional<ClassRoom> optional=this.classRoomRepository.findById(classId);
        if(optional.isEmpty())throw new ApiException("classRoom not found",404);
        return optional.get().getCover();
    }
}
