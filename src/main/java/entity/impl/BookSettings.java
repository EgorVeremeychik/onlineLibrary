package entity.impl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by EgorVeremeychik on 05.06.2016.
 */
public class BookSettings implements Serializable {
    private Book book;
    private List<Genre> genresList;
    private List<Author> authorsList;
    private List<Publisher> publishersList;

    public BookSettings(){}
    public BookSettings(Book book, List<Genre> genresList, List<Author> authorsList, List<Publisher> publishersList){
        this.book = book;
        this.genresList = genresList;
        this.authorsList = authorsList;
        this.publishersList = publishersList;
    }

    public List<Genre> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<Genre> genresList) {
        this.genresList = genresList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Author> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
    }

    public List<Publisher> getPublishersList() {
        return publishersList;
    }

    public void setPublishersList(List<Publisher> publishersList) {
        this.publishersList = publishersList;
    }
}
