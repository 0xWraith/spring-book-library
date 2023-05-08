package com.wraith.dbs.repositories;

import com.wraith.dbs.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, String>
{
    Card findFirstById(String cardId);
}
