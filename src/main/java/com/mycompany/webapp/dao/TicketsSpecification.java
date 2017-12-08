package com.mycompany.webapp.dao;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public final class TicketsSpecification {

    private static final String PASSENGER_ENTITY_FIELD = "passenger";
    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String LAST_NAME_FIELD = "lastName";

    private TicketsSpecification() {
    }

    public static Specification<Ticket> getPassengersByPassenger(String firstName, String lastName) {

        return (root, query, builder) -> {
            Join<Ticket, Passenger> ticketPassengerJoin = root.join(PASSENGER_ENTITY_FIELD);

            return builder.and(
                    builder.equal(ticketPassengerJoin.get(FIRST_NAME_FIELD), firstName),
                    builder.equal(ticketPassengerJoin.get(LAST_NAME_FIELD), lastName));
        };
    }
}
