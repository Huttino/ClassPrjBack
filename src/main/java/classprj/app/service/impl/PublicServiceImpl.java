package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.exception.ApiException;
import classprj.app.mapper.ClassRoomMapper;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.MemberRepository;
import classprj.app.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicServiceImpl implements PublicService {

    private final ClassRoomRepository classRoomRepository;
    private final MemberRepository memberRepository;
    @Autowired
    public PublicServiceImpl(ClassRoomRepository classRoomRepository,MemberRepository memberRepository) {
        this.classRoomRepository = classRoomRepository;
        this.memberRepository=memberRepository;
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
        if(optional.get().getCover()==null) throw new ApiException("cover not present",204);
        return optional.get().getCover();
    }

    @Override
    public List<PublicClassRoomDTO> getClassRoomForLanding() {
        List<ClassRoom> toGet=this.classRoomRepository.findAll();
        toGet.sort((x,y)->{
            if(x.getMembers().size()>y.getMembers().size())
                return -1;
            if(x.getMembers().size()<y.getMembers().size())
                return 1;
            return 0;
        });
        List<PublicClassRoomDTO> toReturn=new ArrayList<>();
        for(int i=0;i<3&&i< toGet.size();i++){
            toReturn.add(ClassRoomMapper.entityToPublicDTO(toGet.get(i)));
        }
        return toReturn;
    }
}
