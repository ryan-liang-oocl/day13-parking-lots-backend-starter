package org.afs.parkinglot.domain.strategies;


import org.afs.parkinglot.domain.ParkingLot;
import org.afs.parkinglot.domain.exception.NoAvailablePositionException;

import java.util.List;

public class SequentiallyStrategy implements ParkingStrategy {

    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots) {
        return  parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }
}
