package ru.otus.spring.hw12.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String name;

    @DBRef
    private Author author;

    private List<String> genres;

    private List<Comment> comments;

    public Book(String id, String name, Author author, List<String> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = genres;
    }

    public Book withId(String id) {
        this.id = id;
        return this;
    }

    public Book withName(String name) {
        this.name = name;
        return this;
    }

    public Book withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Book withGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public Book withGenres(String genres) {
        this.genres = asList(genres.split(","));
        return this;
    }

    public Book withComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}

