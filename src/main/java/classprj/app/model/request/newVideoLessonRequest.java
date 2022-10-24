package classprj.app.model.request;

import java.util.List;

public class newVideoLessonRequest {
    private String youTubeUrl;
    private String title;
    private String description;
    private List<Long> documentAttached;

    public newVideoLessonRequest() {
    }

    public newVideoLessonRequest(String youTubeUrl, String title, String description, List<Long> documentAttached) {
        this.youTubeUrl = youTubeUrl;
        this.title = title;
        this.description = description;
        this.documentAttached = documentAttached;
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

    public List<Long> getDocumentAttached() {
        return documentAttached;
    }

    public void setDocumentAttached(List<Long> documentAttached) {
        this.documentAttached = documentAttached;
    }
}
