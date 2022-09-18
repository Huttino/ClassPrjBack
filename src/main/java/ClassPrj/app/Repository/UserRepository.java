package ClassPrj.app.Repository;

import java.util.Optional;

import javax.crypto.interfaces.DHPublicKey;

import org.springframework.data.jpa.repository.JpaRepository;

import ClassPrj.app.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public boolean existsByUsername(String username);
	public Optional<User> findByUsername(String username);
}
