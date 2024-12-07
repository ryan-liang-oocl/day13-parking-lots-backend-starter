package org.afs.parkinglot.domain;

public record Ticket(String plateNumber, int position, int parkingLot) {
    public Ticket(String plateNumber) {
        this(plateNumber, -1, -1); // Default values for position and parkingLot
    }
}
