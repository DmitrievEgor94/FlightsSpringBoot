package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightDao extends JpaRepository<Flight, Long> {

    List<Flight> findByPlanesPlaneNumber(String planeNumber);
}
