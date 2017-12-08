package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaneDao extends JpaRepository<Plane, Long> {

    @Query("SELECT p FROM Plane p JOIN p.tickets t JOIN t.passenger pas " +
            "WHERE pas.firstName = :firstName AND pas.lastName = :lastName ORDER BY p.planeNumber")
    List<Plane> getPlanesByPassenger(@Param("firstName") String firstName,
                                     @Param("lastName") String lastName);
}
