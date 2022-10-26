package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class newVideoLessonRequest {
    @NotEmpty
    @NotNull
    private String youTubeUrl;
    @NotEmpty
    @NotNull
    private String title;
    private String description;
    private List<Long> documentsAttached;

    public newVideoLessonRequest() {
    }

    public newVideoLessonRequest(String youTubeUrl, String title, String description, List<Long> documentsAttached) {
        this.youTubeUrl = youTubeUrl;
        this.title = title;
        this.description = description;
        this.documentsAttached = documentsAttached;
    }

    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getDocumentsAttached() {
        return documentsAttached;
    }

    public void setDocumentAttached(List<Long> documentsAttached) {
        this.documentsAttached = documentsAttached;
    }
}
