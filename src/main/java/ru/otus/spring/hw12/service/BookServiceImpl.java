package ru.otus.spring.hw12.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.DistinctIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw12.models.Author;
import ru.otus.spring.hw12.models.Book;
import ru.otus.spring.hw12.models.Comment;
import ru.otus.spring.hw12.repositories.BookRepository;

;

@Transactional
@Service
public class BookServiceImpl {
    private final BookRepository bookRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           MongoTemplate mongoTemplate) {
        this.bookRepository = bookRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public String insert(String name, String authorId, String genres) {
        return bookRepository.save(new Book()
                .withName(name)
                .withAuthor(new Author().withId(authorId))
                .withGenres(genres)).getId();
    }

    //    @Override
    public boolean update(String id, String name, String authorId, String genres) {
        Optional<Book> optional = bookRepository.findById(id);
        optional
                .ifPresent(book -> {
                    if (authorId != null) {
                        book.setAuthor(new Author().withId(authorId));
                    }

                    if (genres != null) {
                        book.withGenres(genres);
                    }

                    if (name != null) {
                        book.setName(name);
                    }

                    bookRepository.save(book);
                });

        return optional.isPresent();
    }

//    @Override
    public boolean updateName(String id, String name) {
        return update(id, name, null, null);
    }

//    @Override
    public boolean updateAuthor(String id, String authorId) {
        return update(id, null, authorId, null);
    }

//    @Override
    public boolean updateGenres(String id, String genreId) {
        return update(id, null, null, genreId);
    }

    //    @Override
    public boolean deleteById(String id) {
        bookRepository.deleteById(id);
        return true;
    }

//    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

//    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

//    @Override
    public long count() {
        return bookRepository.count();
    }



    //    @Override
    public boolean updateGenreName(String oldName, String newName) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is(oldName));
//        Update update = new Update();
//        update.set("name", newName);
//        mongoTemplate.updateMulti(query, update, Comment.class);
//        return update(id, null, null, genreId);
        return true;
    }

    public List<String> getGenres() {
        ArrayList<String> genres = new ArrayList<>();
        DistinctIterable<String> iterable = mongoTemplate.getCollection("books").distinct("genres", String.class);
        for (String genre : iterable) {
            genres.add(genre);
        }
        return genres;
    }

    //    @Override
    public long addComment(String bookId, String comment) {
        Optional<Book> optional = bookRepository.findById(bookId);
        optional
                .ifPresent(book -> {
                    List<Comment> comments = book.getComments() == null ? new ArrayList<>() : book.getComments();
                    comments.add(new Comment(comment));
                    book.setComments(comments);
                    bookRepository.save(book);
                });

        return 0;
    }
}
