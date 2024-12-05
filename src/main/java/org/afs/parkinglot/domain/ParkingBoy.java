package org.afs.parkinglot.domain;


import java.util.ArrayList;
import java.util.List;
import org.afs.parkinglot.domain.exception.UnrecognizedTicketException;
import org.afs.parkinglot.domain.strategies.ParkingStrategy;
import org.afs.parkinglot.domain.strategies.SequentiallyStrategy;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy = new SequentiallyStrategy();

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        return parkingStrategy.findParkingLot(parkingLots).park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkingLotOfTheTicket = parkingLots.stream()
            .filter(parkingLot -> parkingLot.contains(ticket))
            .findFirst()
            .orElseThrow(UnrecognizedTicketException::new);
        return parkingLotOfTheTicket.fetch(ticket);
    }
}
