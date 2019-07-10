package common.server.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REQUEST_TABLE")
public class Request {

    @Id
    @GeneratedValue
    private long id;

    private String status;

    private Date date;

    @OneToOne(mappedBy = "request")
    private Document document;

    public Request() {
    }

    public Request(String status, Date date) {
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
