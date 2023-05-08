package com.wraith.dbs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wraith.dbs.utils.Utils;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity(name = "Reservation")
@Table(name = "reservations")
public class Reservation
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String user_id;


    @Column(name = "publication_id", nullable = false)
    private String publication_id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publication publication;

    public Reservation() {}

    public String getId() { return id; }
    public String getUser_id() { return user_id; }
    public String getPublication_id() { return publication_id; }

//    @JsonIgnore
    public OffsetDateTime getCreated_at() { return created_at; }

    public void setId(String id)
    {

        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
