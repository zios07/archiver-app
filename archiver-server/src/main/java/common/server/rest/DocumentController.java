package common.server.rest;

import common.server.domain.Document;
import common.server.exception.NotFoundException;
import common.server.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/documents")
public class DocumentController {

    @Autowired
    private IDocumentService service;

    @GetMapping(value = "download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable long id) throws NotFoundException {
        Document document = service.findDocument(id);
        byte[] file = document.getContent();
        String mimeType = document.getMime();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mimeType));

        String disposition = "document";

        if ("application/pdf".equalsIgnoreCase(mimeType)) {
            disposition = "inline";
        }
        headers.add("content-disposition",
                disposition + "; filename=\"" + document.getName() + "\"");
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @GetMapping(value = "my-documents")
    public ResponseEntity<List<Document>> downloadDocument() throws NotFoundException {
        List<Document> documents = service.findDocumentsForConnectedUser();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Document> findDocument(@PathVariable long id) throws NotFoundException {
        Document document = service.findDocument(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Document>> findAllDocuments() {
        List<Document> documents = service.findAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Document> addDocument(@RequestParam("metadata") String metadata, @RequestParam("document") MultipartFile document) throws IOException {
        Document savedDocument = service.addDocument(metadata, document);
        return new ResponseEntity<Document>(savedDocument, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable long id) throws NotFoundException {
        service.deleteDocument(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Document>> searchDocuments(@RequestParam String username) throws NotFoundException {
        List<Document> documents = service.findDocumentsByUsername(username);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }


}
