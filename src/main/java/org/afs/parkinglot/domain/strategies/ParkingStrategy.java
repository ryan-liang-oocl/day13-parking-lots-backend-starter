package org.afs.parkinglot.domain.strategies;


import org.afs.parkinglot.domain.ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot findParkingLot(List<ParkingLot> parkingLots);
}
