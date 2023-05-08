package com.wraith.dbs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wraith.dbs.utils.Utils;
import com.wraith.dbs.utils.enums.ERentalStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity(name = "Rental")
@Table(name = "rentals")
public class Rental
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "publication_instance_id", nullable = false)
    private String publication_instance_id;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime start_date;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime end_date;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERentalStatus status;

    @ManyToOne
    @JoinColumn(name = "publication_instance_id", insertable = false, updatable = false)
    private PublicationInstance publication_instance;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Rental() { }

    public String getId() { return id; }
    public int getDuration() { return duration; }
    public String getUser_id() { return user_id; }
    public ERentalStatus getStatus() { return status; }

//    @JsonIgnore
    public OffsetDateTime getEnd_date() { return end_date; }

//    @JsonIgnore
    public OffsetDateTime getStart_date() { return start_date; }
    public String getPublication_instance_id() { return publication_instance_id; }

    public void setId(String id)
    {

        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setStatus(ERentalStatus status) { this.status = status; }
    public void setEnd_date(OffsetDateTime end_date) { this.end_date = end_date; }
    public void setStart_date(OffsetDateTime start_date) { this.start_date = start_date; }
    public void setPublication_instance_id(String publication_instance_id) { this.publication_instance_id = publication_instance_id; }
}
