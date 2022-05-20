package com.genspark.SpringBootProject21.Repository;

import com.genspark.SpringBootProject21.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//jpa repository
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
