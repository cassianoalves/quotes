package com.cassianoalves.quotes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Quote {
    @Id
    private String id;
    private String bookId;
    @DBRef
    private User createdBy;
    private String phrase;
    private String author;

    public Quote() {
        // default constructor
    }

    public Quote(String bookId, User createdBy, String phrase, String author) {
        this.bookId = bookId;
        this.createdBy = createdBy;
        this.phrase = phrase;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
