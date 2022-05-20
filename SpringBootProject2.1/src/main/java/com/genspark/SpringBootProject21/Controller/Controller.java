package com.genspark.SpringBootProject21.Controller;

import com.genspark.SpringBootProject21.Entity.Book;
import com.genspark.SpringBootProject21.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BookService bookService;
    //maqpping for CRUD functions
    @GetMapping("/home")
    public String home(){
        return "Hello";
    }
    @GetMapping("/Books")
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
    }
    @GetMapping("/Books/{dds}")
    public ResponseEntity<byte[]> getImage(@PathVariable String dds){return this.bookService.getBookImg(Integer.parseInt(dds));}
    @PostMapping("/Books")
    public Book addBook(@RequestBody Book book){
        return this.bookService.addBook(book);
    }
    @PutMapping("/Books")
    public Book updateBook(@RequestBody Book book){
        return this.bookService.updateBook(book);
    }
    @DeleteMapping("/Books/{dds}")
    public String deleteBook(@PathVariable String dds){
        return this.bookService.deleteBook(Integer.parseInt(dds));
    }
}
