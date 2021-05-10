package com.ztmp.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Response implements Serializable {
    private int status;
    private String message;
    private Timestamp time;
    private List<String> errors;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.time = new Timestamp(System.currentTimeMillis());
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

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
