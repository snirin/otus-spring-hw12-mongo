package ru.otus.spring.hw12.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.hw12.models.Author;
import ru.otus.spring.hw12.models.Book;
import ru.otus.spring.hw12.models.Comment;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author author1;
    private Author author2;

    @ChangeSet(order = "000", id = "dropDB", author = "snirin", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "snirin", runAlways = true)
    public void initAuthors(MongoTemplate template){
        author1 = template.save(new Author().withName("John Smith"));
        author2 = template.save(new Author().withName("Ivan Ivanov"));
    }

    @ChangeSet(order = "002", id = "initStudents", author = "snirin", runAlways = true)
    public void initBooks(MongoTemplate template){
        val book1 = new Book()
                .withName("True love")
                .withAuthor(author1)
                .withGenres("Love story")
//                .withGenres("l")
                .withComments(singletonList(new Comment("Good book")));
        template.save(book1);
        val book2 = new Book()
                .withName("Old cementery")
                .withAuthor(author2)
                .withGenres("Thriller")
//                .withGenres("t")
                .withComments(singletonList(new Comment("Bad book")));
        template.save(book2);
        val book3 = new Book()
                .withName("Sunrise in the empty town")
                .withAuthor(author2)
                .withGenres("Love story,Thriller")
//                .withGenres("l,t")
                .withComments(asList(new Comment("Strange book"), new Comment("Interesting book")));
        template.save(book3);
    }
}
