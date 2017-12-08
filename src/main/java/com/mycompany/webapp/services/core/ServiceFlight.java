package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Flight;

import java.util.List;

public interface ServiceFlight extends ServiceForCrudOperations<Flight> {

    List<Flight> findByPlane(String planeNumber);
}
