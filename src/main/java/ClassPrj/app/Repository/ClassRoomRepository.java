package ClassPrj.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ClassPrj.app.domain.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

}
