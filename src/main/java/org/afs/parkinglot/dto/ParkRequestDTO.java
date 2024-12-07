package org.afs.parkinglot.dto;

public class ParkRequestDTO {
    private String plateNumber;
    private String parkingStrategy;

    public ParkRequestDTO(String plateNumber, String parkingStrategy) {
        this.plateNumber = plateNumber;
        this.parkingStrategy = parkingStrategy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getParkingStrategy() {
        return this.parkingStrategy;
    }

    public void setParkingStrategy(String parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }
}