package com.example.caching.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    
    private final BookRepository bookRepository;
    
    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("================================");
        
        
        System.out.println(".... Fetching books");
        
        System.out.println("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        System.out.println("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        System.out.println("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        System.out.println("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        System.out.println("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        System.out.println("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        System.out.println("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        System.out.println("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        System.out.println("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
        System.out.println("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
        
    
    }
}
