package classprj.app.repository;

import classprj.app.domain.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeRepository extends JpaRepository<Scope,Long> {
}