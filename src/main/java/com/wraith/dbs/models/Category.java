package com.wraith.dbs.models;

import com.wraith.dbs.utils.Utils;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Collection;

@Entity(name = "Category")
@Table(name = "categories")
public class Category
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<PublicationCategory> publicationCategoryCollection;

    public Category() {}

    public Category(String name) { this.name = name; }

    public String getId() { return id; }
    public String getName() { return name; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public Collection<PublicationCategory> getPublicationCategoryCollection() { return publicationCategoryCollection; }

    public void setId(String id)
    {
        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setName(String name) { this.name = name; }
    public void setCreated_at(OffsetDateTime created_at)
    {

        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
    public void setPublicationCategoryCollection(Collection<PublicationCategory> publicationCategoryCollection) { this.publicationCategoryCollection = publicationCategoryCollection; }
}
