package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.v2com.entities.BookEntity;
import org.v2com.repositories.BookRepository;

import java.util.List;
import java.util.UUID;

import static jakarta.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {


    @Inject
    BookRepository repository;

    public List<BookEntity> findAllBooks() {
        return repository.findAllBooks();
    }

    public BookEntity findBookById(UUID id) {
        BookEntity book = repository.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("Livro nao encontrado para o ID fornecido.");
        }
        return book;
    }

    public List<BookEntity> searchBooksByArgs(String title, String author, String tag) {
        return repository.findBooksByArgs(title, author, tag);
    }


    public BookEntity persistBook(@Valid BookEntity bookEntity) {
        return repository.persist(bookEntity);
    }

    public void deleteBook(UUID id) {
        BookEntity bookEntity = repository.findById(id);
        if (bookEntity != null) {
            repository.deleteById(id);
        }
    }

    public BookEntity updateBook(UUID id, @Valid BookEntity bookEntity) {
        BookEntity existingBookEntity = repository.findById(id);

        if (existingBookEntity == null) {
            throw new IllegalArgumentException("Livro nao encontrado para o ID fornecido.");
        }

        existingBookEntity.title = bookEntity.title;
        existingBookEntity.author = bookEntity.author;
        existingBookEntity.description = bookEntity.description;
        existingBookEntity.isbn = bookEntity.isbn;
        existingBookEntity.genre = bookEntity.genre;
        existingBookEntity.cover = bookEntity.cover;
        existingBookEntity.publisher = bookEntity.publisher;
        existingBookEntity.tags = bookEntity.tags;
        existingBookEntity.coverUrl = bookEntity.coverUrl;
        return existingBookEntity;
    }
}