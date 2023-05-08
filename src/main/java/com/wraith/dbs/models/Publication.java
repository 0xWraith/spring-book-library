package com.wraith.dbs.models;

import com.wraith.dbs.dto.authors.AuthorDTO;
import com.wraith.dbs.dto.authors.AuthorNamesDTO;
import com.wraith.dbs.utils.Utils;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Publication")
@Table(name = "publications")
public class Publication
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;


    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<PublicationInstance> publicationInstanceCollection;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<PublicationCategory> publicationCategories;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<PublicationAuthor> publicationAuthors;

    @Transient
    private ArrayList<AuthorNamesDTO> authors;

    @Transient
    private ArrayList<String> categories;


    public Publication() { }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public OffsetDateTime getCreated_at() { return created_at; }
    public OffsetDateTime getUpdated_at() { return updated_at; }
    public ArrayList<String> getCategories() { return categories; }
    public ArrayList<AuthorNamesDTO> getAuthors() { return authors; }

    public void setId(String id)
    {
        if(id == null)
            id = Utils.generateUUID();

        this.id = id;
    }
    public void setTitle(String title) { this.title = title; }
    public void setCreated_at(OffsetDateTime created_at)
    {
        if(created_at == null)
            created_at = OffsetDateTime.now();

        this.created_at = created_at;
    }
    public void setAuthors(ArrayList<AuthorNamesDTO> authors) { this.authors = authors; }
    public void setUpdated_at(OffsetDateTime updated_at) { this.updated_at = updated_at; }
    public void setCategories(ArrayList<String> categories) { this.categories = categories; }

}
