package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.services.core.ServiceFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private ServiceFlight serviceFlight;

    @Autowired
    public FlightController(ServiceFlight serviceFlight) {
        this.serviceFlight = serviceFlight;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Flight flight) {
        String updateMessage = serviceFlight.update(flight);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody Flight flight) {
        String saveMessage = serviceFlight.save(flight);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Flight flight = serviceFlight.read(id);

        return flight == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flight);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        serviceFlight.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/get")
    public ResponseEntity getList() {
        List<Flight> flights = serviceFlight.readAll();

        return flights.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flights);
    }

    @GetMapping(value = "/get-by-plane")
    public ResponseEntity getFlightsByPlane(@RequestParam("plane-number") String planeNumber) {
        List<Flight> flights = serviceFlight.findByPlane(planeNumber);

        return flights.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(flights);
    }
}
