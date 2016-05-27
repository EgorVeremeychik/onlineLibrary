package entity.impl;

import entity.IEntity;

import java.io.Serializable;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class Book implements Serializable, IEntity {

    private long id;
    private String name;
    private Genre genre;
    private String isbn;
    private byte[] image;
    private Author author;
    private int pageCount;
    private byte[] content;
    private int publishDate;
    private String description;
    private Publisher publisher;

    public Book() {
    }

    public Book(long bookID, String name, int pageCount, String isbn, Genre genre, int publishDate,
                Author author, Publisher publisher, byte[] image, String description) {
        this.id = bookID;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.publishDate = publishDate;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
