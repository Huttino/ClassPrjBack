package classprj.app.model.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class UpdateCoverRequest {
    @NotNull
    private MultipartFile cover;

    public UpdateCoverRequest(MultipartFile cover) {
        this.cover = cover;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }
}
