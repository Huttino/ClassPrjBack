package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.exception.ApiException;
import classprj.app.mapper.ClassRoomMapper;
import classprj.app.model.dto.PublicClassRoomDTO;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.MemberRepository;
import classprj.app.service.PublicService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    //TODO set standard pathCover
    @Override
    public byte[] getCover(Long classId) {
        Optional<ClassRoom> optional=this.classRoomRepository.findById(classId);
        if(optional.isEmpty())throw new ApiException("classRoom not found",404);
        String pathCover=optional.get().getPathCover();
        if(pathCover==null) pathCover="E:\\Desktop\\ContentForClassPrj\\standardCover.jpg";
        FileReader reader;
        try {
            return Files.readAllBytes(Paths.get(pathCover));
        } catch (FileNotFoundException e) {
            throw new ApiException(e.toString());
        } catch (IOException ex){
            throw new ApiException("couldn't retrieve the Resource "+ex.toString(), HttpStatus.NOT_ACCEPTABLE.value());
        }

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
