package classprj.app.model.request;

public class newVideoLessonRequest {
    private String youTubeUrl;
    private String title;
    private String description;
    private long[] documentAttached;

    public newVideoLessonRequest() {
    }

    public newVideoLessonRequest(String youTubeUrl, String title, String description, long[] documentAttached) {
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

    public long[] getDocumentAttached() {
        return documentAttached;
    }

    public void setDocumentAttached(long[] documentAttached) {
        this.documentAttached = documentAttached;
    }
}
