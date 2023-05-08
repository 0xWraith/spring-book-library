package com.wraith.dbs.dto.rental;

import com.wraith.dbs.utils.enums.ERentalStatus;

public class RentalFullDTO
{
    private String id;
    private String user_id;
    private String publication_instance_id;
    private int duration;
    private String start_date;
    private String end_date;
    private ERentalStatus status;

    public RentalFullDTO() { }

    public String getId() { return id; }
    public int getDuration() { return duration; }
    public String getUser_id() { return user_id; }
    public String getEnd_date() { return end_date; }
    public ERentalStatus getStatus() { return status; }
    public String getStart_date() { return start_date; }
    public String getPublication_instance_id() { return publication_instance_id; }

    public void setId(String id) { this.id = id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setStatus(ERentalStatus status) { this.status = status; }
    public void setEnd_date(String end_date) { this.end_date = end_date; }
    public void setStart_date(String start_date) { this.start_date = start_date; }
    public void setPublication_instance_id(String publication_instance_id) { this.publication_instance_id = publication_instance_id; }
}
