package com.wraith.dbs.dto;

import jakarta.persistence.*;

public class PublicationCategoryDTO
{
    private long id;
    private String category_id;
    private String publication_id;

    public PublicationCategoryDTO() {}

    public long getId() { return id; }
    public String getCategory_id() { return category_id; }
    public String getPublication_id() { return publication_id; }

    public void setId(long id) { this.id = id; }
    public void setCategory_id(String category_id) { this.category_id = category_id; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
