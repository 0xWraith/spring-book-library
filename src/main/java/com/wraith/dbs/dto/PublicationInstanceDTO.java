package com.wraith.dbs.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wraith.dbs.interfaces.IDTO;
import com.wraith.dbs.utils.enums.EPublicationInstanceStatus;
import com.wraith.dbs.utils.enums.EPublicationInstanceType;

import java.time.OffsetDateTime;

public class PublicationInstanceDTO implements IDTO
{
    private String id;
    private String publication_id;
    private EPublicationInstanceType type;
    private String publisher;
    private int year;
    private EPublicationInstanceStatus status;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    public PublicationInstanceDTO() {}

    public String getId() { return id; }
    public int getYear() { return year; }
    public String getPublisher() { return publisher; }
    public EPublicationInstanceType getType() { return type; }
    public String getPublication_id() { return publication_id; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public EPublicationInstanceStatus getStatus() { return status; }

    public void setId(String id) { this.id = id; }
    public void setYear(int year) { this.year = year; }
    public void setType(EPublicationInstanceType type) { this.type = type; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setStatus(EPublicationInstanceStatus status) { this.status = status; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }

    @Override
    public boolean isDTOInvalid()
    {

        if(publication_id == null || publication_id.isEmpty())
            return true;

        if(type == null)
            return true;

        if(publisher == null || publisher.isEmpty())
            return true;

        if(year < 0)
            return true;

        if(status == null)
            status = EPublicationInstanceStatus.available;

        return false;
    }
}
