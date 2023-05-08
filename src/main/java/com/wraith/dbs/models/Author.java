package com.wraith.dbs.models;

import com.wraith.dbs.utils.Utils;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Collection;

@Entity(name = "Authors")
@Table(name = "authors")
public class Author
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<PublicationAuthor> publicationAuthorCollection;

    public Author() { }

    public Author(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }

    public void setId(String id)
    {

        if(id == null || id.isEmpty())
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setCreated_at(OffsetDateTime created_at)
    {

        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
}
