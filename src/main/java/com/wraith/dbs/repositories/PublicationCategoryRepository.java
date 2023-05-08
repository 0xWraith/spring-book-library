package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Category;
import com.wraith.dbs.models.PublicationCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PublicationCategoryRepository extends JpaRepository<PublicationCategory, Long>
{
    @Query("SELECT pc FROM PublicationCategory AS pc WHERE pc.publication_id = :publicationId")
    ArrayList<PublicationCategory> findAllByPublicationId(String publicationId);

    @Query("SELECT new Category (c.name) FROM PublicationCategory AS pc JOIN Category AS c on c.id = pc.category_id WHERE pc.publication_id = :publicationId")
    ArrayList<Category> findAllPublicationCategories(String publicationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PublicationCategory AS pc WHERE pc.publication_id = :publicationId")
    void deleteAllPublicationCategories(String publicationId);
}
