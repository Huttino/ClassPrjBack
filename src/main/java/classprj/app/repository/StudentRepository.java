package classprj.app.repository;

import java.util.Optional;

import classprj.app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{
	public Optional<Student> findByUsername(String username);
}
