package org.v2com.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.v2com.entities.Book;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookRepository {

    @Inject
    EntityManager entityManager;

    public Book findById(UUID id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAllBooks() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public void persist(Book book) {
        entityManager.persist(book);
    }
    public void deleteById(UUID id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    public List<Book> findBooksByArgs(String title, String author, String tag) {
        // Construir a consulta dinamicamente com base nos parâmetros fornecidos
        StringBuilder queryBuilder = new StringBuilder("SELECT b FROM Book b WHERE 1=1");

        if (title != null && !title.isEmpty()) {
            queryBuilder.append(" AND b.title LIKE :title");
        }
        if (author != null && !author.isEmpty()) {
            queryBuilder.append(" AND b.author LIKE :author");
        }
        if (tag != null && !tag.isEmpty()) {
            queryBuilder.append(" AND b.tags LIKE :tag");
        }

        // Criar a query com a string montada
        Query query = entityManager.createQuery(queryBuilder.toString());

        // Definir os parâmetros dinamicamente na consulta
        if (title != null && !title.isEmpty()) {
            query.setParameter("title", "%" + title + "%");
        }
        if (author != null && !author.isEmpty()) {
            query.setParameter("author", "%" + author + "%");
        }
        if (tag != null && !tag.isEmpty()) {
            query.setParameter("tag", "%" + tag + "%");
        }

        // Executar a consulta e retornar os resultados
        return query.getResultList();
    }
}
