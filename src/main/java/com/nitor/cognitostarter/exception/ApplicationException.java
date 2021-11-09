package com.nitor.cognitostarter.exception;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    public static final String UNHANDLED_EXCEPTION_TXT = "Unhandled Exception !!! ";
    private static final long serialVersionUID = 4265758284484258031L;
    private String message;
    private int errorCodeId;
    private HttpStatus status;

    public ApplicationException(final int errorCodeId) {
        this.errorCodeId = errorCodeId;
    }

    public ApplicationException(final String message, final int errorCodeId) {
        this.setMessage(message);
        this.errorCodeId = errorCodeId;
    }

    public ApplicationException(final Throwable throwable) {
        super(throwable);
        this.errorCodeId = errorCodeId;
        this.setMessage(message);
    }

    public ApplicationException(String message, int errorCodeId, HttpStatus status) {
        super();
        this.message = message;
        this.errorCodeId = errorCodeId;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCodeId() {
        return errorCodeId;
    }

    public void setErrorCodeId(int errorCodeId) {
        this.errorCodeId = errorCodeId;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String convertToJson() throws JsonProcessingException {
        if (this == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(this);
    }
}