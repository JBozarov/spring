package com.tekcamp.users.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private final String resourceName;

    private final String fieldName;

    public String getResourceName() {
        return resourceName;
    }

    public Object getFieldName() {
        return fieldName;
    }

    public UserNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}