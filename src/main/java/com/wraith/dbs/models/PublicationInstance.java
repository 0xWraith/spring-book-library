package com.wraith.dbs.models;

import com.wraith.dbs.utils.Utils;
import com.wraith.dbs.utils.enums.EPublicationInstanceStatus;
import com.wraith.dbs.utils.enums.EPublicationInstanceType;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "PublicationInstance")
@Table(name = "publication_instances")
public class PublicationInstance
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "publication_id", nullable = false)
    private String publication_id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPublicationInstanceType type;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPublicationInstanceStatus status;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "publication_id", insertable = false, updatable = false)
    private Publication publication;

    @OneToMany(mappedBy = "publication_instance", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Rental> rentalCollection;

    public PublicationInstance() {}

    public String getId() { return id; }
    public int getYear() { return year; }
    public String getPublisher() { return publisher; }
    public EPublicationInstanceType getType() { return type; }
    public String getPublication_id() { return publication_id; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public EPublicationInstanceStatus getStatus() { return status; }

    public void setId(String id)
    {
        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setYear(int year) { this.year = year; }
    public void setType(EPublicationInstanceType type) { this.type = type; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setStatus(EPublicationInstanceStatus status)
    {

        if(status == null)
            status = EPublicationInstanceStatus.available;

        this.status = status;
    }
    public void setCreated_at(OffsetDateTime created_at)
    {
        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }

}
