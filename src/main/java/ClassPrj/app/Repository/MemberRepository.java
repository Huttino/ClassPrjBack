package ClassPrj.app.Repository;

import ClassPrj.app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByClassRoomIdAndStudentId(Long classRoomId, Long studentId);
    boolean existsMembersByClassRoomIdAndGradeIsNull(Long classRoomId);
}
