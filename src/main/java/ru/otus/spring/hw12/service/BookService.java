package ru.otus.spring.hw12.service;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.hw12.models.Book;

public interface BookService {
    boolean update(String id, String name, String authorId, String genreId);

    boolean updateName(String id, String name);

    boolean updateAuthor(String id, String authorId);

    boolean updateGenre(String id, String genreId);

    Book save(Book book);

    boolean deleteById(String id);

    Optional<Book> findById(String id);

    List<Book> findAll();

    long count();
}
