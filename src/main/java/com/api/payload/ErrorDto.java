package com.api.payload;

import java.util.Date;

public class ErrorDto {
    public ErrorDto(Date date, String message, String uri) {
        this.date = date;
        this.uri = uri;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }

    private String message;
    private Date date;
    private String uri;

}
