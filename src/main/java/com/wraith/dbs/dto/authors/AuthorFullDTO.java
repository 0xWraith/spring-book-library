package com.wraith.dbs.dto.authors;

import java.time.OffsetDateTime;

public class AuthorFullDTO extends AuthorDTO
{
    private String id;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    public AuthorFullDTO() {}

    public String getId() { return id; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    public void setId(String id) { this.id = id; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
}
