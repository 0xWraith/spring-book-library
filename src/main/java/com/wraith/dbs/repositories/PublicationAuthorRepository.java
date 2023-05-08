package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Author;
import com.wraith.dbs.models.PublicationAuthor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, Long>
{
    @Query("SELECT new PublicationAuthor(pa.id, pa.author_id, pa.publication_id) FROM PublicationAuthor AS pa WHERE pa.publication_id = :publicationId")
    ArrayList<PublicationAuthor> findAllPublicationId(String publicationId);

    @Query("SELECT new Authors (a.name, a.surname) FROM PublicationAuthor AS pa JOIN Authors AS a on a.id = pa.author_id WHERE pa.publication_id = :publicationId")
    ArrayList<Author> findAllPublicationAuthors(String publicationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PublicationAuthor AS pa WHERE pa.publication_id = :publicationId")
    void deleteAllPublicationAuthors(String publicationId);
}
