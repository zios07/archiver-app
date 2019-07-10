package common.server.service.impl;

import com.google.gson.Gson;
import common.server.domain.Document;
import common.server.domain.Request;
import common.server.domain.User;
import common.server.exception.NotFoundException;
import common.server.repository.DocumentRepository;
import common.server.service.IDocumentService;
import common.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Document savedDocument = documentRepository.getOne(document.getId());
        savedDocument.getRequest().setStatus("Archived");
        savedDocument.setLastModif(new Date());
        return documentRepository.save(savedDocument);
    }

    @Override
    public Document findDocument(long id) throws NotFoundException {
        return documentRepository.findById(id).get();
    }

    @Override
    public List<Document> findAllDocuments() {
        return documentRepository.findAll().stream().map(document -> {
            document.setContent(null);
            return document;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteDocument(long id) throws NotFoundException {
        documentRepository.deleteById(id);
    }

    @Override
    public List<Document> findDocumentsByUsername(String username) throws NotFoundException {
        return documentRepository.findByUserAccountUsername(username).stream().map(document -> {
            document.setContent(null);
            return document;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Document> findDocumentsForConnectedUser() throws NotFoundException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.findDocumentsByUsername(username);
    }

    @Override
    public Document addDocument(String metadata, MultipartFile file) throws IOException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByUsername(username);
        Request request = new Request("Not Archived", new Date());
        Document document = new Gson().fromJson(metadata, Document.class);
        document.setName(file.getOriginalFilename());
        document.setContent(file.getBytes());
        document.setSize(file.getSize());
        document.setUser(user);
        document.setRequest(request);
        document.setLastModif(new Date());
        document.setMime(file.getContentType());
        return this.addDocument(document);
    }
}
