package org.afs.parkinglot.domain;

import org.afs.parkinglot.domain.exception.UnrecognizedTicketException;
import org.afs.parkinglot.domain.strategies.AvailableRateStrategy;
import org.afs.parkinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.parkinglot.domain.strategies.SequentiallyStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {

    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    void should_initialize_correctly_with_three_parking_lots_and_three_parking_boys() {
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();
        assertEquals(3, parkingLots.size());
        assertNotNull(parkingManager.getParkingBoyByStrategy(new SequentiallyStrategy()));
        assertNotNull(parkingManager.getParkingBoyByStrategy(new MaxAvailableStrategy()));
        assertNotNull(parkingManager.getParkingBoyByStrategy(new AvailableRateStrategy()));
    }

    @Test
    void should_return_all_parking_lots_when_getAllParkingLots_is_called() {
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();
        assertEquals(3, parkingLots.size());
        assertEquals("The Plaza Park", parkingLots.get(0).getName());
        assertEquals("City Mall Garage", parkingLots.get(1).getName());
        assertEquals("Office Tower Parking", parkingLots.get(2).getName());
    }

    @Test
    void should_request_correct_parking_boy_to_park_car_and_return_valid_ticket_when_park_is_called() {
        String plateNumber = "ABC123";
        Ticket ticket = parkingManager.park(plateNumber, new SequentiallyStrategy());
        assertNotNull(ticket);
        assertEquals(plateNumber, ticket.plateNumber());
    }

    @Test
    void should_fetch_car_from_corresponding_parking_lot_and_return_correct_car_when_fetch_is_called() {
        String plateNumber = "ABC123";
        Ticket ticket = parkingManager.park(plateNumber, new SequentiallyStrategy());
        Car fetchedCar = parkingManager.fetch(ticket);
        assertNotNull(fetchedCar);
        assertEquals(plateNumber, fetchedCar.plateNumber());
    }

    @Test
    void should_throw_exception_when_fetch_is_called_with_invalid_ticket() {
        Ticket invalidTicket = new Ticket("INVALID123", 0, 0);
        assertThrows(UnrecognizedTicketException.class, () -> parkingManager.fetch(invalidTicket));
    }
}