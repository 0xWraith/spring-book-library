package com.wraith.dbs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wraith.dbs.interfaces.IDTO;
import com.wraith.dbs.models.Rental;
import com.wraith.dbs.models.Reservation;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class UserDTO implements IDTO
{
    private String id;
    private String name;
    private String surname;
    private String email;
    private String birth_date;
    private ArrayList<Rental> rentals;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
    private String personal_identificator;
    private ArrayList<Reservation> reservations;

    public UserDTO() { }

    @Override
    public boolean isDTOInvalid()
    {
        if(name == null || name.isEmpty())
            return true;

        if(surname == null || surname.isEmpty())
            return true;

        if(email == null || email.isEmpty())
            return true;

        if(birth_date == null || birth_date.isEmpty() || !birth_date.matches("^\\d{4}-\\d{2}-\\d{2}$"))
            return true;

        return personal_identificator == null || personal_identificator.isEmpty();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSurname() { return surname; }
    public String getBirth_date() { return birth_date; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<Rental> getRentals() { return rentals; }

    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<Reservation> getReservations() { return reservations; }

    public String getPersonal_identificator() { return personal_identificator; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setRentals(ArrayList<Rental> rentals) { this.rentals = rentals; }
    public void setBirth_date(String birth_date) { this.birth_date = birth_date; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at;}
    public void setReservations(ArrayList<Reservation> reservations) { this.reservations = reservations; }
    public void setPersonal_identificator(String personal_identificator) { this.personal_identificator = personal_identificator; }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", rentals=" + rentals +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", personal_identificator='" + personal_identificator + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
