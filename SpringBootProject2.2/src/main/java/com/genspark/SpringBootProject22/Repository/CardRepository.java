package com.genspark.SpringBootProject22.Repository;

import com.genspark.SpringBootProject22.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
