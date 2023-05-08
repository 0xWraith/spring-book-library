package com.wraith.dbs.models;

import jakarta.persistence.*;

@Entity(name = "PublicationCategory")
@Table(name = "publication_categories")
public class PublicationCategory
{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_id", nullable = false)
    private String category_id;

    @Column(name = "publication_id", nullable = false)
    private String publication_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publication publication;

    public PublicationCategory() {}

    public long getId() { return id; }
    public String getCategory_id() { return category_id; }
    public String getPublication_id() { return publication_id; }

    public void setId(long id) { this.id = id; }
    public void setCategory_id(String category_id) { this.category_id = category_id; }
    public void setPublication_id(String publication_id) { this.publication_id = publication_id; }
}
