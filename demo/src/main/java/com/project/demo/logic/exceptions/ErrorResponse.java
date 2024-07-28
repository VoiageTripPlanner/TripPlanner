package com.project.demo.logic.exceptions;

public class ErrorResponse {
    private final int status;
    private final String error;
    private final String title;
    private final String detail;
    private final String timestamp;

    public ErrorResponse(int status, String error, String title, String detail, String timestamp) {
        this.status = status;
        this.error = error;
        this.title = title;
        this.detail = detail;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }
}
