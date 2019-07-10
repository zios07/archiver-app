package common.server.service;

import common.server.domain.Document;
import common.server.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDocumentService {

	Document addDocument(Document document);

	Document updateDocument(Document document);

	Document findDocument(long id) throws NotFoundException;

	List<Document> findAllDocuments();

	void deleteDocument(long id) throws NotFoundException;

	List<Document> findDocumentsByUsername(String username) throws NotFoundException;

    Document addDocument(String metadata, MultipartFile document) throws IOException;
}
