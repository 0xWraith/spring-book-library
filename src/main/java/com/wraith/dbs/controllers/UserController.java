package com.wraith.dbs.controllers;

import com.wraith.dbs.dto.UserDTO;
import com.wraith.dbs.models.Rental;
import com.wraith.dbs.models.Reservation;
import com.wraith.dbs.models.User;
import com.wraith.dbs.services.RentalService;
import com.wraith.dbs.services.ReservationService;
import com.wraith.dbs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.stream.Collectors.toCollection;

@RestController
@RequestMapping("/users")
public class UserController
{

    private final UserService userService;
    private final RentalService rentalService;
    private final ReservationService reservationService;

    @Autowired
    public UserController(UserService userService, RentalService rentalService, ReservationService reservationService)
    {
        this.userService = userService;
        this.rentalService = rentalService;
        this.reservationService = reservationService;
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO)
    {

        if(userDTO.isDTOInvalid())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = userService.findFirstByEmail(userDTO.getEmail());

        if(user != null)
        {
            LocalDate birthdate = LocalDate.parse(userDTO.getBirth_date(), DateTimeFormatter.ISO_LOCAL_DATE);

            if(Period.between(birthdate, LocalDate.now()).getYears() >= 18)
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user = userService.findById(userDTO.getId());

        if (user != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        user = userService.save(userService.convertToEntity(userDTO));

        if(userDTO.getReservations() != null)
            userDTO.getReservations().forEach(reservationService::save);

        if(userDTO.getRentals() != null)
            userDTO.getRentals().forEach(rentalService::save);

        return new ResponseEntity<>(userService.convertToDTO(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId)
    {
        User user = userService.findFirstById(userId);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.setRentals(new ArrayList<>(user.getRentalCollection()));
        user.setReservations(new ArrayList<>(user.getReservationCollection()));

        return new ResponseEntity<>(userService.convertToDTO(user), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO)
    {

        User user = userService.findFirstById(userId);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User userByEmail = userService.findFirstByEmail(userDTO.getEmail());

        if(userByEmail != null && !Objects.equals(userByEmail.getId(), user.getId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if(userDTO.getName() != null)
            user.setName(userDTO.getName());

        if(userDTO.getSurname() != null)
            user.setSurname(userDTO.getSurname());

        if(userDTO.getBirth_date() != null)
            user.setBirth_date(userDTO.getBirth_date());

        if(userDTO.getPersonal_identificator() != null)
            user.setPersonal_identificator(userDTO.getPersonal_identificator());

        if(userDTO.getEmail() != null)
            user.setEmail(userDTO.getEmail());

        user = userService.save(user);

        return new ResponseEntity<>(userService.convertToDTO(user), HttpStatus.OK);
    }
}
