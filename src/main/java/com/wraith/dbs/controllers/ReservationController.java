package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.ReservationDTO;
import com.wraith.dbs.models.Reservation;
import com.wraith.dbs.services.PublicationService;
import com.wraith.dbs.services.ReservationService;
import com.wraith.dbs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController
{
    private final UserService userService;
    private final PublicationService publicationService;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(UserService userService, PublicationService publicationService, ReservationService reservationService)
    {
        this.userService = userService;
        this.publicationService = publicationService;
        this.reservationService = reservationService;
    }

    @PostMapping("")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO)
    {

        if(userService.findFirstById(reservationDTO.getUser_id()) == null || publicationService.findFirstById(reservationDTO.getPublication_id()) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Reservation reservation = new Reservation();

        reservation.setId(reservationDTO.getId());
        reservation.setUser_id(reservationDTO.getUser_id());
        reservation.setPublication_id(reservationDTO.getPublication_id());

        reservation = reservationService.save(reservation);

        return new ResponseEntity<>(reservationService.convertToDTO(reservation), HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable String reservationId)
    {
        Reservation reservation = reservationService.findFirstById(reservationId);

        if(reservation == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(reservationService.convertToDTO(reservation));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable String reservationId)
    {
        Reservation reservation = reservationService.findFirstById(reservationId);

        if(reservation == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        reservationService.delete(reservation);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
