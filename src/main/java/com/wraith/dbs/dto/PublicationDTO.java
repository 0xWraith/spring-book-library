package com.wraith.dbs.dto;

import com.wraith.dbs.dto.authors.AuthorFullDTO;
import com.wraith.dbs.dto.authors.AuthorNamesDTO;
import com.wraith.dbs.interfaces.IDTO;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class PublicationDTO implements IDTO
{
    private String id;
    private String title;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
    private ArrayList<AuthorNamesDTO> authors;
    private ArrayList<String> categories;

    public PublicationDTO() { }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public ArrayList<AuthorNamesDTO> getAuthors() { return authors; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public ArrayList<String> getCategories() { return categories; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthors(ArrayList<AuthorNamesDTO> authors) { this.authors = authors; }
    public void setCreated_at(OffsetDateTime created_at) { this.created_at = created_at; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
    public void setCategories(ArrayList<String> categories) { this.categories = categories; }


    @Override
    public String toString()
    {
        return "PublicationDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", authors=" + authors +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean isDTOInvalid()
    {

        if(title == null || title.isEmpty())
            return true;

        if(authors == null || authors.isEmpty())
            return true;

        return categories == null || categories.isEmpty();
    }
}
