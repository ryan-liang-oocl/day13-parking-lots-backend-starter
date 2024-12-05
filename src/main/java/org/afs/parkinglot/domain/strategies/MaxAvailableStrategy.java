package org.afs.parkinglot.domain.strategies;


import org.afs.parkinglot.domain.ParkingLot;
import org.afs.parkinglot.domain.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class MaxAvailableStrategy implements ParkingStrategy{
    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
