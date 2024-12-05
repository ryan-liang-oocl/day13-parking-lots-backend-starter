package org.afs.parkinglot.domain.exception;

public class UnrecognizedTicketException extends RuntimeException {
    public UnrecognizedTicketException() {
        super("Unrecognized parking ticket.");
    }
}
