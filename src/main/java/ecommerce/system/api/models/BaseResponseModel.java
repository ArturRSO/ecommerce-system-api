package ecommerce.system.api.models;

import java.time.LocalDateTime;

public class BaseResponseModel<T> {

    private boolean isSuccess;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public BaseResponseModel() {
        this.timestamp = LocalDateTime.now();
    }

    public BaseResponseModel(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
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
