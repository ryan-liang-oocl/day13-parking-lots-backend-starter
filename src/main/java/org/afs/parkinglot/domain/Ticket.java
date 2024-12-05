package org.afs.parkinglot.domain;

public record Ticket(String plateNumber, int position, int parkingLot) {
}
