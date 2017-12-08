package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerDao extends JpaRepository<Passenger, Long> {

    @Query("SELECT p FROM Passenger p JOIN p.tickets t GROUP BY p HAVING COUNT(t.id) > 1")
    List<Passenger> getPassengersWithSeveralTickets();

    Long countByTicketsFlightFlightNumber(String flightNumber);
}
