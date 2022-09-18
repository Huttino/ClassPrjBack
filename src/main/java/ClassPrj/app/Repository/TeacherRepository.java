package ClassPrj.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ClassPrj.app.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
	public Optional<Teacher> findByUsername(String username);
}
