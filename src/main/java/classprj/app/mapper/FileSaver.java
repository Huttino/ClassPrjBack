package classprj.app.mapper;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver {

    private static String classRoomDataPath="E:/Desktop/ContentForClassPrj";

    public static String saveFile(Long classId, boolean document, MultipartFile file) throws IOException {
        Path path;

        if(!document) path=Paths.get(classRoomDataPath+"/"+classId+"/cover."+file.getOriginalFilename().split(".")[1]);
        else path=Paths.get(classRoomDataPath+"/"+classId+"/documents"+"/"+file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return path.toString();
    }

    public static void createDirs(Long classId) throws IOException {
        Path path=Paths.get(classRoomDataPath+"/"+classId+"/documents");
        Files.createDirectories(path);
    }
}
