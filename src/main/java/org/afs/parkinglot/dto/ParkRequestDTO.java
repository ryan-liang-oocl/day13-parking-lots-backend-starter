package org.afs.parkinglot.dto;

import org.afs.parkinglot.domain.strategies.ParkingStrategy;

public class ParkRequestDTO {
    private String plateNumber;
    private String parkingBoyStrategy;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getParkingBoyStrategy() {
        return this.parkingBoyStrategy;
    }

    public void setParkingBoyStrategy(String parkingBoyStrategy) {
        this.parkingBoyStrategy = parkingBoyStrategy;
    }
}