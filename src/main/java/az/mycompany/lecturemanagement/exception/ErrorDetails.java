package az.mycompany.lecturemanagement.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

    private int status;
    private String message;
    private String details;
    private String path;
    private LocalDateTime timestamp;

    public ErrorDetails(int status, String message, String details, String path, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
