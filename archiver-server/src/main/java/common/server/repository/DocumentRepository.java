package common.server.repository;

import common.server.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByUserAccountUsername(String username);

    List<Document> findByRequestStatus(String not_archived);
}
