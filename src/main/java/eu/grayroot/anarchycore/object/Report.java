package eu.grayroot.anarchycore.object;

import java.sql.Timestamp;

public class Report {

    int id;
    String content;
    String server;
    String authorName;
    Timestamp date;
    boolean status;

    public Report(int id, String content, String server, String authorName, Timestamp date, boolean status) {
        this.id = id;
        this.content = content;
        this.server = server;
        this.authorName = authorName;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
