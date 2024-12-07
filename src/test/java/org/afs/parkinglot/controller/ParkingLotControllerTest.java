package org.afs.parkinglot.controller;

import org.afs.parkinglot.domain.Car;
import org.afs.parkinglot.domain.ParkingLot;
import org.afs.parkinglot.domain.ParkingManager;
import org.afs.parkinglot.domain.Ticket;
import org.afs.parkinglot.dto.ParkRequestDTO;
import org.afs.parkinglot.dto.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ParkingLotController.class)
class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private ParkingManager parkingManager = Mockito.mock(ParkingManager.class);

    private List<ParkingLot> parkingLots;

    @BeforeEach
    void setUp() {
        ParkingLot plazaPark = new ParkingLot(1, "The Plaza Park", 9);
        ParkingLot cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
        ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);
        parkingLots = Arrays.asList(plazaPark, cityMallGarage, officeTowerParking);
    }

    @Test
    void shouldReturnAllParkingLots() throws Exception {
        Mockito.when(parkingManager.getAllParkingLots()).thenReturn(parkingLots);

        mockMvc.perform(MockMvcRequestBuilders.get("/parking-lot")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("The Plaza Park")))
                .andExpect(jsonPath("$[1].name", is("City Mall Garage")))
                .andExpect(jsonPath("$[2].name", is("Office Tower Parking")));
    }

    @Test
    void shouldParkCar() throws Exception {
        ParkRequestDTO parkRequestDTO = new ParkRequestDTO("ABC123", "SequentiallyStrategy");
        Ticket ticket = new Ticket("ABC123", 1, 1);
        Mockito.when(parkingManager.park(anyString(), anyString())).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/parking-lot/park")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"plateNumber\":\"ABC123\",\"parkingStrategy\":\"SequentiallyStrategy\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plateNumber", is("ABC123")));
    }

}