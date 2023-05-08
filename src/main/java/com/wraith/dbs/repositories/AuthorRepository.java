package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String>
{
    Author findFirstById(String id);
    Author findByNameEqualsIgnoreCaseAndSurnameEqualsIgnoreCase(String name, String surname);
}
