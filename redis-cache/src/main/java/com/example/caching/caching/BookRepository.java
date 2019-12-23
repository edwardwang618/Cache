package com.example.caching.caching;

public interface BookRepository {
    Book getByIsbn(String isbn);
    Book saveOrUpdate(Book book);
    void deleteByIsbn(String isbn);
}
