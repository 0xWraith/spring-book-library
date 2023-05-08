package com.wraith.dbs.dto.rental;

public class RentalInfoDTO
{
    private String id;
    private int duration;
    private String user_id;
    private String publication_id;

    public RentalInfoDTO() {}

    public String getId() { return id; }
    public int getDuration() { return duration; }
    public String getUser_id() { return user_id; }
    public String getPublication_id() { return publication_id; }

    public void setId(String id) { this.id = id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
