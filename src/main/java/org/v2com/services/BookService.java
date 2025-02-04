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

    //  manipular a lógica de negócios

    @Transactional(SUPPORTS)
    public List<Book> findAllBooks() {
        return Book.listAll();
    }

    @Transactional(SUPPORTS)
    public Book findBookById(long id) {
        return Book.findById(id);
    }

    public Book persistBook(@Valid Book book) {
        book.persist();
        return book;
    }

    public void deleteBook(UUID id) {
        Book.deleteById(id);
    }

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
        existingBook.status = book.status;
        existingBook.language = book.language;
        existingBook.publisher = book.publisher;
        existingBook.tags = book.tags;
        existingBook.coverUrl = book.coverUrl;

        existingBook.persist();

        return existingBook;
    }



}