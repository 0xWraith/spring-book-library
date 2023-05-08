package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.rental.RentalInfoDTO;
import com.wraith.dbs.models.PublicationInstance;
import com.wraith.dbs.models.Rental;
import com.wraith.dbs.models.Reservation;
import com.wraith.dbs.services.PublicationInstanceService;
import com.wraith.dbs.services.RentalService;
import com.wraith.dbs.services.ReservationService;
import com.wraith.dbs.utils.enums.EPublicationInstanceStatus;
import com.wraith.dbs.utils.enums.ERentalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/rentals")
public class RentalController
{
    private final RentalService rentalService;
    private final ReservationService reservationService;
    private final PublicationInstanceService publicationInstanceService;

    @Autowired
    public RentalController(RentalService rentalService, ReservationService reservationService, PublicationInstanceService publicationInstanceService)
    {
        this.rentalService = rentalService;
        this.reservationService = reservationService;
        this.publicationInstanceService = publicationInstanceService;
    }

    @PostMapping("")
    public ResponseEntity<?> createRental(@RequestBody RentalInfoDTO rentalInfoDTO)
    {

        Reservation reservation = reservationService.findFirstByPublicationId(rentalInfoDTO.getPublication_id());


        if(reservation != null && !Objects.equals(reservation.getUser_id(), rentalInfoDTO.getUser_id()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        PublicationInstance publicationInstance = publicationInstanceService.findAvailablePublicationInstanceForRental(rentalInfoDTO.getPublication_id(), EPublicationInstanceStatus.available);

        if(publicationInstance == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Rental rental = new Rental();
        OffsetDateTime now = OffsetDateTime.now();

        rental.setStart_date(now);
        rental.setId(rentalInfoDTO.getId());
        rental.setStatus(ERentalStatus.active);
        rental.setUser_id(rentalInfoDTO.getUser_id());
        rental.setDuration(rentalInfoDTO.getDuration());
        rental.setEnd_date(now.plusDays(rentalInfoDTO.getDuration()));
        rental.setPublication_instance_id(publicationInstance.getId());

        publicationInstance.setStatus(EPublicationInstanceStatus.reserved);

        rental = rentalService.save(rental);
        publicationInstanceService.save(publicationInstance);

        if(reservation != null)
            reservationService.delete(reservation);

        return new ResponseEntity<>(rentalService.convertToDTO(rental), HttpStatus.CREATED);
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<?> getRental(@PathVariable String rentalId)
    {

        Rental rental = rentalService.findFirstById(rentalId);

        if(rental == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(rentalService.convertToDTO(rental), HttpStatus.OK);
    }

    @PatchMapping("/{rentalId}")
    public ResponseEntity<?> updateRental(@PathVariable String rentalId, @RequestBody RentalInfoDTO rentalInfoDTO)
    {

        Rental rental = rentalService.findFirstById(rentalId);

        if(rental == null)
            return ResponseEntity.notFound().build();

        int duration = rentalInfoDTO.getDuration();

        if(duration < 0 || duration > 14)
            return ResponseEntity.badRequest().build();

        rental.setDuration(rentalInfoDTO.getDuration());
        rental.setEnd_date(OffsetDateTime.now().plusDays(rentalInfoDTO.getDuration()));

        rental = rentalService.save(rental);

        return new ResponseEntity<>(rentalService.convertToDTO(rental), HttpStatus.OK);
    }
}
