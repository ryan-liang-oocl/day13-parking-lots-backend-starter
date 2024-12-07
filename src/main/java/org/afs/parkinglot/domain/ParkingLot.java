package org.afs.parkinglot.domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.afs.parkinglot.domain.exception.NoAvailablePositionException;
import org.afs.parkinglot.domain.exception.UnrecognizedTicketException;

public class ParkingLot {
    private int id;
    private String name;
    private final Map<Ticket, Car> tickets = new HashMap<>();

    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableCapacity() {
        return capacity - tickets.size();
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new NoAvailablePositionException();
        }

        Ticket ticket = new Ticket(car.plateNumber(), tickets.size() + 1, this.id);
        tickets.put(ticket, car);
        return ticket;
    }

    public boolean isFull() {
        return capacity == tickets.size();
    }

    public Car fetch(Ticket ticket) {
        return tickets.entrySet().stream()
                .filter(entry -> entry.getKey().plateNumber().equals(ticket.plateNumber()))
                .findFirst()
                .map(entry -> tickets.remove(entry.getKey()))
                .orElseThrow(UnrecognizedTicketException::new);
    }

    public boolean contains(Ticket ticket) {
        return tickets.keySet().stream()
                .anyMatch(t -> t.plateNumber().equals(ticket.plateNumber()));
    }

    public double getAvailablePositionRate() {
        return getAvailableCapacity() / (double) capacity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getTickets() {
        return tickets.keySet().stream().toList();
    }

}
