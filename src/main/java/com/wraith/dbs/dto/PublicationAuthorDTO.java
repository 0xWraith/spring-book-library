package com.wraith.dbs.dto;

import jakarta.persistence.*;

public class PublicationAuthorDTO
{
    private long id;
    private String author_id;
    private String publication_id;

    public PublicationAuthorDTO() {}

    public long getId() { return id; }
    public String getAuthor_id() { return author_id; }
    public String getPublication_id() { return publication_id; }

    public void setId(long id) { this.id = id; }
    public void setAuthor_id(String author_id) { this.author_id = author_id; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
