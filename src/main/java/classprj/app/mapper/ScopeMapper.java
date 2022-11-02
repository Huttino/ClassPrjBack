package classprj.app.mapper;

import classprj.app.domain.Scope;
import classprj.app.model.dto.ScopeDto;

public class ScopeMapper {
    public static ScopeDto entityToDto(Scope x) {
        ScopeDto toReturn=new ScopeDto(x.getId(),x.getTitle(),x.getDescription());
        return toReturn;
    }
}
