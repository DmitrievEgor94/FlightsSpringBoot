package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Ticket;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceTicket extends ServiceForCrudOperations<Ticket> {

    List<Ticket> getTicketsByPassenger(String firstName, String lastName);

    List<BigDecimal> getCostOfTickets();
}
