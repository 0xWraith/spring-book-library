package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>
{
    Category findFirstById(String id);
    Category findFirstByNameEqualsIgnoreCase(String name);
}
