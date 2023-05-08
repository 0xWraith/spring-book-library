package com.wraith.dbs.dto;

import com.wraith.dbs.interfaces.IDTO;
import com.wraith.dbs.utils.enums.ECardStatus;

import java.time.OffsetDateTime;

public class CardDTO implements IDTO
{
    private String id;
    private String user_id;
    private String magstripe;
    private ECardStatus status;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    public CardDTO() { }

    public String getId() { return id; }
    public String getUser_id() { return user_id; }
    public String getMagstripe() { return magstripe; }
    public ECardStatus getStatus() { return status; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    public void setId(String id) { this.id = id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setMagstripe(String magstripe) { this.magstripe = magstripe; }
    public void setStatus(ECardStatus status) { this.status = status; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }

    @Override
    public boolean isDTOInvalid()
    {
        if (user_id == null || user_id.isEmpty())
            return true;

        if (magstripe == null || magstripe.isEmpty())
            return true;

        if (status == null)
            return true;

        return false;
    }
}

