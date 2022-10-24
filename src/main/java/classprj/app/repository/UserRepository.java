package classprj.app.repository;

import java.util.Optional;

import classprj.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	public boolean existsByUsername(String username);
	public Optional<User> findByUsername(String username);
}
