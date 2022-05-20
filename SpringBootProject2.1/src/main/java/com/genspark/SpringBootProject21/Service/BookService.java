package com.genspark.SpringBootProject21.Service;

import com.genspark.SpringBootProject21.Entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    ResponseEntity<byte[]> getBookImg(int index);
    Book addBook(Book book);
    Book updateBook(Book book);
    String deleteBook(int index);
}
