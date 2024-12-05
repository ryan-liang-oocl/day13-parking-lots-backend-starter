package org.afs.parkinglot.domain.exception;

public class NoAvailablePositionException extends RuntimeException {
    public NoAvailablePositionException() {
        super("No available position.");
    }
}
