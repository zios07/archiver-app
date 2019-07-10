package common.server.service.impl;

import com.google.gson.Gson;
import common.server.domain.Account;
import common.server.domain.Document;
import common.server.domain.User;
import common.server.exception.NotFoundException;
import common.server.repository.AccountRepository;
import common.server.repository.DocumentRepository;
import common.server.service.IAccountService;
import common.server.service.IDocumentService;
import common.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService implements IDocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private IUserService userService;

	@Override
	public Document addDocument(Document document) {
		return documentRepository.save(document);
	}

	@Override
	public Document updateDocument(Document document) {
		return documentRepository.save(document);
	}

	@Override
	public Document findDocument(long id) throws NotFoundException {
		return documentRepository.findById(id).get();
	}

	@Override
	public List<Document> findAllDocuments() {
		return documentRepository.findAll();
	}

	@Override
	public void deleteDocument(long id) throws NotFoundException {
		documentRepository.deleteById(id);
	}

	@Override
	public List<Document> findDocumentsByUsername(String username) throws NotFoundException {
		return documentRepository.findByUserAccountUsername(username);
	}

	@Override
	public Document addDocument(String metadata, MultipartFile file) throws IOException {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.userService.getUserByUsername(username);
		Document document = new Gson().fromJson(metadata, Document.class);
		document.setName(file.getOriginalFilename());
		document.setContent(file.getBytes());
		document.setSize(file.getSize());
		document.setUser(user);
		return document;
	}
}
