package org.afs.parkinglot.domain;

import org.afs.parkinglot.domain.exception.UnrecognizedTicketException;
import org.afs.parkinglot.domain.strategies.AvailableRateStrategy;
import org.afs.parkinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.parkinglot.domain.strategies.ParkingStrategy;
import org.afs.parkinglot.domain.strategies.SequentiallyStrategy;

import java.util.Arrays;
import java.util.List;

public class ParkingManager {
    public static final String SEQUENTIALLY_STRATEGY = "SequentiallyStrategy";
    public static final String MAX_AVAILABLE_STRATEGY = "MaxAvailableStrategy";
    public static final String AVAILABLE_RATE_STRATEGY = "AvailableRateStrategy";
    public static final String UNKNOWN_PARKING_STRATEGY = "Unknown parking strategy";
    private final List<ParkingLot> parkingLots;
    private final ParkingBoy standardParkingBoy;
    private final ParkingBoy superParkingBoy;
    private final ParkingBoy superSmartParkingBoy;

    public ParkingManager() {
        ParkingLot plazaPark = new ParkingLot(1, "The Plaza Park", 9);
        ParkingLot cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
        ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);

        this.parkingLots = Arrays.asList(plazaPark, cityMallGarage, officeTowerParking);

        this.standardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
        this.superParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
        this.superSmartParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public Ticket park(String plateNumber, ParkingStrategy strategy) {
        ParkingBoy parkingBoy = getParkingBoyByStrategy(strategy);
        Car car = new Car(plateNumber);
        return parkingBoy.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .findFirst()
                .map(parkingLot -> parkingLot.fetch(ticket))
                .orElseThrow(UnrecognizedTicketException::new);
    }

    private ParkingBoy getParkingBoyByStrategy(ParkingStrategy strategy) {
        return switch (strategy.getClass().getSimpleName()) {
            case SEQUENTIALLY_STRATEGY -> standardParkingBoy;
            case MAX_AVAILABLE_STRATEGY -> superParkingBoy;
            case AVAILABLE_RATE_STRATEGY -> superSmartParkingBoy;
            default -> throw new IllegalArgumentException(UNKNOWN_PARKING_STRATEGY);
        };
    }
}