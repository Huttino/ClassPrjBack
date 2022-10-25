package classprj.app.repository;

import java.util.Optional;

import classprj.app.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
	public Optional<Teacher> findByUsername(String username);
}
