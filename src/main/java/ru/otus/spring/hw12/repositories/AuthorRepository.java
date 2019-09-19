package ru.otus.spring.hw12.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw12.models.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
