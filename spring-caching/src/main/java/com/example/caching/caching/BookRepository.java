package com.example.caching.caching;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
