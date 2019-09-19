package ru.otus.spring.hw12.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw12.models.Author;
import ru.otus.spring.hw12.models.Book;
import ru.otus.spring.hw12.repositories.AuthorRepository;
import ru.otus.spring.hw12.service.BookServiceImpl;

@ShellComponent
public class ShellCommands {
    private final AuthorRepository authorRepository;
    private final BookServiceImpl bookService;

    public ShellCommands(AuthorRepository authorRepository,
                         BookServiceImpl bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    /*
    AUTHORS
     */
    @ShellMethod(value = "Author:Insert", key = {"ai"})
    public String authorInsert(@ShellOption String name) {
        return authorRepository.save(new Author().withName(name)).getId();
    }

    @ShellMethod(value = "Author:Update", key = {"au"})
    public boolean authorUpdate(@ShellOption String id, @ShellOption String name) {
        authorRepository.save(new Author(id, name));
        return true;
    }

    @ShellMethod(value = "Author:Delete", key = {"ad"})
    public boolean authorDelete(@ShellOption String id) {
        authorRepository.deleteById(id);
        return true;
    }

    @ShellMethod(value = "Author:Get", key = {"ag"})
    public String authorGet(@ShellOption String id) {
        return authorRepository.findById(id).map(Author::toString).orElse(null);
    }

    @ShellMethod(value = "Author:GetAll", key = {"aga"})
    public String authorGet() {
        return authorRepository.findAll().toString();
    }

    @ShellMethod(value = "Author:Count", key = {"ac"})
    public long authorCount() {
        return authorRepository.count();
    }
    /*
    AUTHORS - END
     */

    /*
    BOOKS
     */
    @ShellMethod(value = "Book:Insert", key = {"bi"})
    public String bookInsert(@ShellOption String name, @ShellOption String authorId,
                             @ShellOption(defaultValue = "") String genres) {
        return bookService.insert(name, authorId, genres);
    }

    @ShellMethod(value = "Book:Update", key = {"bu"})
    public boolean bookUpdate(@ShellOption String id, @ShellOption String name, @ShellOption String authorId,
                              @ShellOption String genreId) {
        return bookService.update(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Book:UpdateName", key = {"bun"})
    public boolean bookUpdateName(@ShellOption String id, @ShellOption String name) {
        return bookService.updateName(id, name);
    }

    @ShellMethod(value = "Book:UpdateAuthor", key = {"bua"})
    public boolean bookUpdateAuthor(@ShellOption String id, @ShellOption String authorId) {
        return bookService.updateAuthor(id, authorId);
    }

    @ShellMethod(value = "Book:UpdateGenres", key = {"bug"})
    public boolean bookUpdateGenre(@ShellOption String id, @ShellOption String genres) {
        return bookService.updateGenres(id, genres);
    }

    @ShellMethod(value = "Book:Delete", key = {"bd"})
    public boolean bookDelete(@ShellOption String id) {
        return bookService.deleteById(id);
    }

    @ShellMethod(value = "Book:Get", key = {"bg"})
    public String bookGet(@ShellOption String id) {
        return bookService.findById(id).map(Book::toString).orElse("null");
    }

    @ShellMethod(value = "Book:GetAll", key = {"bga"})
    public String bookGet() {
        return bookService.findAll().toString();
    }

    @ShellMethod(value = "Book:Count", key = {"bc"})
    public long bookCount() {
        return bookService.count();
    }
    /*
    BOOKS - END
     */

    /*
    GENRES
     */
    @ShellMethod(value = "Genre:Update", key = {"gu"})
    public boolean GenreUpdate(@ShellOption String oldName, @ShellOption String newName) {
        return bookService.updateGenreName(oldName, newName);
    }

    @ShellMethod(value = "Genre:GetAll", key = {"gga"})
    public String GenreGetAll() {
        return bookService.getGenres().toString();
    }
    /*
    GENRES - END
     */


    /*
    COMMENTS
     */
    @ShellMethod(value = "Comment:Insert", key = {"ci"})
    public long commentInsert(@ShellOption String bookId, @ShellOption String text) {
        return bookService.addComment(bookId, text);
    }

//    @ShellMethod(value = "Comment:Update", key = {"cu"})
//    public boolean commentUpdate(@ShellOption long id, @ShellOption String text) {
//        return commentService.update(id, text);
//    }
//
//    @ShellMethod(value = "Comment:Delete", key = {"cd"})
//    public boolean commentDelete(@ShellOption long id) {
//        commentRepository.deleteById(id);
//        return true;
//    }
//
//    @ShellMethod(value = "Comment:Get", key = {"cg"})
//    public String commentGet(@ShellOption long id) {
//        return commentRepository.findById(id).map(Comment::toString).orElse(null);
//    }
//
//    @ShellMethod(value = "Comment:GetAll", key = {"cga"})
//    public String commentGet() {
//        return commentRepository.findAll().toString();
//    }
//
//    @ShellMethod(value = "Comment:GetByBook", key = {"cgb"})
//    public String commentGetByBook(@ShellOption long bookId) {
//        return commentRepository.findByBook_Id(bookId).toString();
//    }
//
//    @ShellMethod(value = "Comment:Count", key = {"cc"})
//    public long commentCount() {
//        return commentRepository.count();
//    }
    /*
    COMMENTS - END
     */
}
