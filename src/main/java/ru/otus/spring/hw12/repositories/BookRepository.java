package ru.otus.spring.hw12.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw12.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {
//    List<String> findDistinctByGenres
//    Collection<String> findAllByGenres
}
