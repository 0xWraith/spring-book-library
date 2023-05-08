package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, String>
{

    Rental findFirstById(String rentalId);
}
