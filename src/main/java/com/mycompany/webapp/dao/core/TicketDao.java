package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    @Query("SELECT t.cost from Ticket t")
    List<BigDecimal> getCostsOfTickets();
}
