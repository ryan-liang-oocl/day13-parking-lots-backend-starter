package org.afs.parkinglot.controller;

import org.afs.parkinglot.domain.Car;
import org.afs.parkinglot.domain.ParkingLot;
import org.afs.parkinglot.domain.ParkingManager;
import org.afs.parkinglot.domain.Ticket;
import org.afs.parkinglot.dto.FetchRequestDTO;
import org.afs.parkinglot.dto.ParkRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lot")
@CrossOrigin
public class ParkingLotController {

    private final ParkingManager parkingManager;

    public ParkingLotController() {
        this.parkingManager = new ParkingManager();
    }


    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingManager.getAllParkingLots();
    }

    @PostMapping("/park")
    public Ticket park(@RequestBody ParkRequestDTO parkRequestDTO) {
        return parkingManager.park(parkRequestDTO.getPlateNumber(), parkRequestDTO.getParkingBoyStrategy());
    }

    @PostMapping("/fetch")
    public Car fetch(@RequestBody FetchRequestDTO fetchRequestDTO) {
        Ticket ticket = new Ticket(fetchRequestDTO.getPlateNumber()); // Assuming position and parkingLot are not needed for fetch
        return parkingManager.fetch(ticket);
    }
}