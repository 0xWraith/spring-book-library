package com.wraith.dbs.models;

import jakarta.persistence.*;

@Entity(name = "PublicationAuthor")
@Table(name = "publication_authors")
public class PublicationAuthor
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author_id", nullable = false)
    private String author_id;

    @Column(name = "publication_id", nullable = false)
    private String publication_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publication publication;

    public PublicationAuthor() {}

    public PublicationAuthor(long id, String author_id, String publication_id)
    {
        this.id = id;
        this.author_id = author_id;
        this.publication_id = publication_id;
    }

    public long getId() { return id; }
    public String getAuthor_id() { return author_id; }
    public String getPublication_id() { return publication_id; }

    public void setId(long id) { this.id = id; }
    public void setAuthor_id(String author_id) { this.author_id = author_id; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
