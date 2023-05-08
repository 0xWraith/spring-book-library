package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Publication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, String>
{
    Publication findFirstById(String id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Publication AS p WHERE p.id = :id")
    void deletePublication(String id);
}
