package com.mycompany.webapp.controllers;

import com.mycompany.price_service.PriceService;
import com.mycompany.price_service.PriceServiceImpl;
import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private ServiceTicket serviceTicket;
    private PriceService priceService;

    @Autowired
    public TicketController(ServiceTicket serviceTicket, PriceServiceImpl priceService) {
        this.serviceTicket = serviceTicket;
        this.priceService = priceService;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Ticket ticket) {
        String updateMessage = serviceTicket.update(ticket);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody Ticket ticket) {
        String saveMessage = serviceTicket.save(ticket);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Ticket ticket = serviceTicket.read(id);

        return ticket == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(ticket);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        serviceTicket.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity getList() {
        List<Ticket> tickets = serviceTicket.readAll();

        return tickets.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(tickets);
    }

    @GetMapping(value = "/get-by-passenger")
    public ResponseEntity getPlanesByPassenger(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName) {
        List<Ticket> tickets = serviceTicket.getTicketsByPassenger(firstName, lastName);

        return tickets.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(tickets);
    }

    @GetMapping(value = "/get-average-price-of-tickets")
    public ResponseEntity getAveragePrice() {
        List<BigDecimal> costOfTickets = serviceTicket.getCostOfTickets();

        return costOfTickets.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(priceService.getAveragePrice(costOfTickets));
    }
}
