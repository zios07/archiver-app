package common.server.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DOCUMENT_TABLE")
public class Document {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double size;

    private String directory;

    @Lob
    private byte[] content;

    private Date lastModif;

    private String type;

    private String extension;

    private String description;

    private String designation;

    private String mime;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Request request;


    public Document() {
    }

    public Document(String name, double size, String directory, byte[] content, Date lastModif, String type, String extension, String description, String designation, User user, Request request) {
        this.name = name;
        this.size = size;
        this.directory = directory;
        this.content = content;
        this.lastModif = lastModif;
        this.type = type;
        this.extension = extension;
        this.description = description;
        this.designation = designation;
        this.user = user;
        this.request = request;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Date getLastModif() {
        return lastModif;
    }

    public void setLastModif(Date lastModif) {
        this.lastModif = lastModif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
