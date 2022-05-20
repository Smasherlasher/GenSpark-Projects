package com.genspark.SpringBootProject21.Entity;

import javax.persistence.*;

@Entity
@Table(name="tbl_Books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dds;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private boolean inStatus;
    @Column
    private String ImageFile;

    public Book(String title, String author, int dds, boolean inStatus,String imageFile){
        this.title = title;
        this.author = author;
        this.dds = dds;
        this.inStatus = inStatus;
        this.ImageFile = imageFile;
    }

    public Book() {
    }

    public String getImageFile() {
        return ImageFile;
    }

    public void setImageFile(String imageFile) {
        ImageFile = imageFile;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDds() {
        return dds;
    }

    public void setDds(int dds) {
        this.dds = dds;
    }

    public boolean isInStatus() {
        return inStatus;
    }

    public void setInStatus(boolean inStatus) {
        this.inStatus = inStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dds=" + dds +
                ", inStatus=" + inStatus +
                '}';
    }
}
