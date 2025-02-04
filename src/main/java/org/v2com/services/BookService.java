package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.Book;

import java.util.List;

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

    public void deleteBook(long id) {
        Book.deleteById(id);
    }


}