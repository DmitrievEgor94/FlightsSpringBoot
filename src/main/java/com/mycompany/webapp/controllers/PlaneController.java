package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServicePlane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/planes")
public class PlaneController {

    private ServicePlane servicePlane;

    @Autowired
    public PlaneController(ServicePlane servicePlane) {
        this.servicePlane = servicePlane;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Plane plane) {
        String updateMessage = servicePlane.update(plane);

        return updateMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateMessage);
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody Plane plane) {
        String saveMessage = servicePlane.save(plane);

        return saveMessage == null ? null
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveMessage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable(value = "id") Long id) {
        Plane plane = servicePlane.read(id);

        return plane == null ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(plane);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        servicePlane.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity getList() {
        List<Plane> planes = servicePlane.readAll();

        return planes.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(planes);
    }

    @GetMapping(value = "/get-by-passenger")
    public ResponseEntity getPlanesByPassenger(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName) {
        List<Plane> planes = servicePlane.getPlanesByPassenger(firstName, lastName);

        return planes.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(planes);
    }
}
