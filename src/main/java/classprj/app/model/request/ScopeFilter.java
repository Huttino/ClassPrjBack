package classprj.app.model.request;

import java.util.List;

public class ScopeFilter {
    List<Long> scopesId;

    public ScopeFilter(List<Long> scopesId) {
        this.scopesId = scopesId;
    }

    public ScopeFilter() {
    }

    public List<Long> getScopesId() {
        return scopesId;
    }

    public void setScopesId(List<Long> scopesId) {
        this.scopesId = scopesId;
    }
}
