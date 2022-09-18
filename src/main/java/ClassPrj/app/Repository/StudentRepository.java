package ClassPrj.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ClassPrj.app.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	public Optional<Student> findByUsername(String username);
}
