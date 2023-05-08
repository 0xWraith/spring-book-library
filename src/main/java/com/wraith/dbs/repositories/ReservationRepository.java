package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String>
{
    Reservation findFirstById(String reservationId);

    @Query("SELECT r FROM Reservation AS r WHERE r.publication_id = :publicationId ORDER BY r.created_at ASC LIMIT 1")
    Reservation findFirstByPublicationId(String publicationId);
}
