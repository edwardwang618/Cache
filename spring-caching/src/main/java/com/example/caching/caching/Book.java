package com.example.caching.caching;

import lombok.Data;

@Data
public class Book {
    private String isbn;
    private String title;
    
    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
    
}
