package com.wraith.dbs.dto;

import com.wraith.dbs.interfaces.IDTO;

import java.time.OffsetDateTime;

public class CategoryDTO implements IDTO
{
    private String id;
    private String name;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    public CategoryDTO() {}

    public String getId() { return id; }
    public String getName() { return name; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }

    @Override
    public boolean isDTOInvalid() { return name == null || name.isEmpty() || !name.matches("^[a-zA-Z ]{1,255}$"); }

    @Override
    public String toString() { return "CategoryDTO{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", created_at=" + created_at + ", updated_at=" + updated_at + '}'; }
}
