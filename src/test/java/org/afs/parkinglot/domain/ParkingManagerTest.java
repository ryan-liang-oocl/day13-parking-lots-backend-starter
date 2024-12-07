package org.afs.parkinglot.domain;

import org.afs.parkinglot.domain.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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
        assertNotNull(parkingManager.getParkingBoyByStrategy(ParkingManager.SEQUENTIALLY_STRATEGY));
        assertNotNull(parkingManager.getParkingBoyByStrategy(ParkingManager.MAX_AVAILABLE_STRATEGY));
        assertNotNull(parkingManager.getParkingBoyByStrategy(ParkingManager.AVAILABLE_RATE_STRATEGY));
    }

    @Test
    void should_return_all_parking_lots_when_getAllParkingLots_is_called() {
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();
        assertEquals(3, parkingLots.size());
        assertEquals("The Plaza Park", parkingLots.get(0).getName());
        assertEquals("City Mall Garage", parkingLots.get(1).getName());
        assertEquals("Office Tower Parking", parkingLots.get(2).getName());
    }

    @ParameterizedTest
    @MethodSource("provideParkingStrategies")
    void should_request_correct_parking_boy_to_park_car_and_return_valid_ticket_when_park_is_called(String strategy) {
        String plateNumber = "ABC123";
        Ticket ticket = parkingManager.park(plateNumber, strategy);
        assertNotNull(ticket);
        assertEquals(plateNumber, ticket.plateNumber());
    }

    private static Stream<Arguments> provideParkingStrategies() {
        return Stream.of(
                Arguments.of(ParkingManager.SEQUENTIALLY_STRATEGY),
                Arguments.of(ParkingManager.MAX_AVAILABLE_STRATEGY),
                Arguments.of(ParkingManager.AVAILABLE_RATE_STRATEGY)
        );
    }

    @Test
    void should_fetch_car_from_corresponding_parking_lot_and_return_correct_car_when_fetch_is_called() {
        String plateNumber = "ABC123";
        Ticket ticket = parkingManager.park(plateNumber, ParkingManager.SEQUENTIALLY_STRATEGY);
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