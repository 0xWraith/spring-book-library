package com.wraith.dbs.models;

import com.wraith.dbs.utils.Utils;
import com.wraith.dbs.utils.enums.ECardStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity(name = "Card")
@Table(name = "user_cards")
public class Card
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "magstripe", nullable = false)
    private String magstripe;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ECardStatus status;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Card() { }

    public String getId() { return id; }
    public String getUser_id() { return user_id; }
    public String getMagstripe() { return magstripe; }
    public ECardStatus getStatus() { return status; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    public void setId(String id)
    {

        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setMagstripe(String magstripe) { this.magstripe = magstripe; }
    public void setStatus(ECardStatus status) { this.status = status; }
    public void setCreated_at(OffsetDateTime created_at)
    {

        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
}
