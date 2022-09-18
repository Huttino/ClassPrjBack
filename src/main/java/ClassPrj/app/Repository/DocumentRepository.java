package ClassPrj.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ClassPrj.app.domain.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
