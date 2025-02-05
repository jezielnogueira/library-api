package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.Book;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {

    //TODO Busca com Filtragem

    //  manipular a lógica de negócios

    public List<Book> findAllBooks() {
        return Book.listAll();
    }

    public Book findBookById(UUID id) {
        return Book.findById(id);
    }

    public List<Book> findBooksByArgs(String title, String author, String tag) {
        StringBuilder query = new StringBuilder("SELECT b FROM Book b WHERE 1=1");
        if (title != null && !title.isEmpty()) {
            query.append(" AND b.title LIKE :title");
        }
        if (author != null && !author.isEmpty()) {
            query.append(" AND b.author LIKE :author");
        }
        if (tag != null && !tag.isEmpty()) {
            query.append(" AND b.tags LIKE :tag");
        }
        return Book.list(query.toString(),
                title != null ? "%" + title + "%" : "",
                author != null ? "%" + author + "%" : "",
                tag != null ? "%" + tag + "%" : "");
    }

    @Transactional(SUPPORTS)
    public Book persistBook(@Valid Book book) {
        book.persist();
        return book;
    }

    @Transactional(SUPPORTS)
    public void deleteBook(UUID id) {
        Book.deleteById(id);
    }

    @Transactional(SUPPORTS)
    public Book updateBook(UUID id, @Valid Book book) {
        Book existingBook = Book.findById(id);

        if (existingBook == null) {
            throw new IllegalArgumentException("Livro nao encontrado para o ID fornecido.");
        }

        existingBook.title = book.title;
        existingBook.author = book.author;
        existingBook.description = book.description;
        existingBook.isbn = book.isbn;
        existingBook.genre = book.genre;
        existingBook.cover = book.cover;
        existingBook.publisher = book.publisher;
        existingBook.tags = book.tags;
        existingBook.coverUrl = book.coverUrl;

        existingBook.persist();

        return existingBook;
    }



}