package classprj.app.repository;

import classprj.app.domain.VideoLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoLessonRepository extends JpaRepository<VideoLesson,Long> {
}
