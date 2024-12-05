package org.afs.parkinglot.domain.strategies;


import org.afs.parkinglot.domain.ParkingLot;
import org.afs.parkinglot.domain.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class AvailableRateStrategy implements ParkingStrategy{
    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
