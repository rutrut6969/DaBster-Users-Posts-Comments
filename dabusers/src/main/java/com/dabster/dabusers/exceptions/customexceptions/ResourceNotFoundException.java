package com.dabster.dabusers.exceptions.customexceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super("ERROR FROM DABSTER API " + message);
    }
}
