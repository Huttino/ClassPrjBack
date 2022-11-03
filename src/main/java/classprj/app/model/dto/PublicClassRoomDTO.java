package classprj.app.model.dto;

import java.util.List;

public class PublicClassRoomDTO {
    private Long id;
    private String title;
    private String description;
    private List<ScopeDto> scopes;

    public PublicClassRoomDTO(Long id, String title, String description,List<ScopeDto> scopes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.scopes=scopes;
    }

    public PublicClassRoomDTO() {
    }

    public List<ScopeDto> getScopes() {
        return scopes;
    }

    public void setScopes(List<ScopeDto> scopes) {
        this.scopes = scopes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
