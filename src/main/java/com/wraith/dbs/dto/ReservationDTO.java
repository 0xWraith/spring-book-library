package com.wraith.dbs.dto;

public class ReservationDTO
{
    private String id;
    private String user_id;
    private String created_at;
    private String publication_id;

    public ReservationDTO() {}

    public String getId() { return id; }
    public String getUser_id() { return user_id; }
    public String getCreated_at() { return created_at; }
    public String getPublication_id() { return publication_id; }

    public void setId(String id) { this.id = id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
