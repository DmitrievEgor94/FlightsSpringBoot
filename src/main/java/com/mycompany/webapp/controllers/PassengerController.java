package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.core.ServicePassenger;
import com.mycompany.webapp.services.core.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private ServicePassenger servicePassenger;

    @Autowired
    private ServiceTicket serviceTicket;

    @Autowired
    public PassengerController(ServicePassenger servicePassenger) {
        this.servicePassenger = servicePassenger;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Passenger passenger) {
        String updateMessage = servicePassenger.update(passenger);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody Passenger passenger) {
        String saveMessage = servicePassenger.save(passenger);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Passenger passenger = servicePassenger.read(id);

        return passenger == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passenger);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        servicePassenger.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/get")
    public ResponseEntity getList() {
        List<Passenger> passengers = servicePassenger.readAll();

        return passengers.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passengers);
    }

    @GetMapping(value = "/get-with-several-tickets")
    public ResponseEntity getPassengersWithSeveralTickets() {
        List<Passenger> passengers = servicePassenger.getPassengersWithSeveralTickets();

        return passengers.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(passengers);
    }

    @GetMapping("/count-by-flight-number")
    public ResponseEntity countPassengersByFlightNumber(@RequestParam("flightNumber") String flightNumber) {
        return ResponseEntity.ok(servicePassenger.countPassengersByFlight(flightNumber));
    }
}