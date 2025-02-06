package org.v2com.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.v2com.entities.BookEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookRepository {

    @Inject
    EntityManager entityManager;

    public BookEntity findById(UUID id) {
        return entityManager.find(BookEntity.class, id);
    }

    public List<BookEntity> findAllBooks() {
        return entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class).getResultList();
    }

    public void persist(BookEntity bookEntity) {
        entityManager.persist(bookEntity);
    }
    public void deleteById(UUID id) {
        BookEntity bookEntity = entityManager.find(BookEntity.class, id);
        if (bookEntity != null) {
            entityManager.remove(bookEntity);
        }
    }

    public List<BookEntity> findBooksByArgs(String title, String author, String tag) {
        StringBuilder queryBuilder = new StringBuilder("SELECT b FROM BookEntity b WHERE 1=1");
        if (title != null && !title.isEmpty()) {
            queryBuilder.append(" AND b.title LIKE :title");
        }
        if (author != null && !author.isEmpty()) {
            queryBuilder.append(" AND b.author LIKE :author");
        }
        if (tag != null && !tag.isEmpty()) {
            queryBuilder.append(" AND b.tags LIKE :tag");
        }

        Query query = entityManager.createQuery(queryBuilder.toString());
        if (title != null && !title.isEmpty()) {
            query.setParameter("title", "%" + title + "%");
        }
        if (author != null && !author.isEmpty()) {
            query.setParameter("author", "%" + author + "%");
        }
        if (tag != null && !tag.isEmpty()) {
            query.setParameter("tag", "%" + tag + "%");
        }

        return query.getResultList();
    }
}
