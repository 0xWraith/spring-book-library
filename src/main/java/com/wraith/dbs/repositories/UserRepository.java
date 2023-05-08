package com.wraith.dbs.repositories;

import com.wraith.dbs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    User findFirstByEmail(String email);

    User findFirstById(String id);
}
