#Cache Redis with Spring Cache

Similar with cache cassandra with spring cache,
we first run the saveOrUpdate to save a book into Redis,
then get the book by isbn, we can see the second query finishes immediately 
after the first one, suggesting that cache works well.
```java
@Test
void getByIsbnTest() {
    System.out.println(simpleBookRepository.getByIsbn("isbn1"));
    System.out.println(simpleBookRepository.getByIsbn("isbn1"));
}

@Test
void saveOrUpdate() {
    Book book = new Book();
    book.setIsbn("isbn1");
    book.setTitle("Title: isbn1");
    simpleBookRepository.saveOrUpdate(book);
}
```