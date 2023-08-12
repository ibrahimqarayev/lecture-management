package az.mycompany.lecturemanagement.dto.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String status;
    private int httpStatusCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String status, int httpStatusCode, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
