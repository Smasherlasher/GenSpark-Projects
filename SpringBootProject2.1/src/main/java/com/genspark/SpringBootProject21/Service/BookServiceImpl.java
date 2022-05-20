package com.genspark.SpringBootProject21.Service;

import com.genspark.SpringBootProject21.Entity.Book;

import com.genspark.SpringBootProject21.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl() {

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //method to retrieve an image assoiciated with the book
    @Override
    public ResponseEntity<byte[]> getBookImg(int index) {
        try {
            Optional<Book> book = this.bookRepository.findById(index);
            byte[] imageBytes = null;
            if(book.isPresent()) {
                ClassPathResource imageFile = new ClassPathResource(book.get().getImageFile());
                imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
            }else{
                throw  new RuntimeException("Book not Found with ID " + index);
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //method to add a book
    @Override
    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }
    //method to update a book

    @Override
    public Book updateBook(Book book){
        return this.bookRepository.save(book);
    }
    //method to delete a book

    @Override
    public String deleteBook(int index){
        String bookTitle;
        if(this.bookRepository.findById(index).isPresent()) {
            bookTitle = this.bookRepository.findById(index).get().getTitle();
        }else{
            throw  new RuntimeException("Book not Found with ID" + index);
        }
        this.bookRepository.deleteById(index);
        return  bookTitle + " Has been removed";
    }

}
