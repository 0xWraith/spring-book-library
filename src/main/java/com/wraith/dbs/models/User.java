package com.wraith.dbs.models;

import com.wraith.dbs.utils.Utils;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "User")
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private String birth_date;

    @Column(name = "personal_id", nullable = false)
    private String personal_identificator;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Card> cards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Rental> rentalCollection;


    @Transient
    private ArrayList<Rental> rentals;

    @Transient
    private ArrayList<Reservation> reservations;

    public User() { }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSurname() { return surname; }
    public Collection<Card> getCards() { return cards; }
    public String getBirth_date() { return birth_date; }
    public ArrayList<Rental> getRentals() { return rentals; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public ArrayList<Reservation> getReservations() { return reservations; }
    public String getPersonal_identificator() { return personal_identificator; }

    public Collection<Rental> getRentalCollection() { return rentalCollection; }
    public Collection<Reservation> getReservationCollection() { return reservationCollection; }

    public void setId(String id)
    {
        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setCards(Collection<Card> cards) { this.cards = cards; }
    public void setRentals(ArrayList<Rental> rentals) { this.rentals = rentals; }
    public void setBirth_date(String birth_date) { this.birth_date = birth_date; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at;}
    public void setCreated_at(OffsetDateTime created_at)
    {
        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setReservations(ArrayList<Reservation> reservations) { this.reservations = reservations; }
    public void setRentalCollection(Collection<Rental> rentalCollection) { this.rentalCollection = rentalCollection; }
    public void setPersonal_identificator(String personal_identificator) { this.personal_identificator = personal_identificator; }
    public void setReservationCollection(Collection<Reservation> reservationCollection) { this.reservationCollection = reservationCollection; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", personal_identificator='" + personal_identificator + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
