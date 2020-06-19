package com.dabster.dabusers.exceptions.customexceptions;

public class ResourceFoundException extends RuntimeException {

    public ResourceFoundException(String message) {
        super("ERROR FROM DABSTER API " + message);
    }
}
