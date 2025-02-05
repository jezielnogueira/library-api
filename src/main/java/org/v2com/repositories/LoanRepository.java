package org.v2com.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.v2com.entities.Loan;

import java.util.UUID;

@ApplicationScoped
public class LoanRepository {
    @Inject
    EntityManager entityManager;

    public void persist(Object entity) {
        entityManager.persist(entity);
    }
    public Loan findById(UUID id) {
        return entityManager.find(Loan.class, id);
    }
    public void update(Object entity) {
        entityManager.merge(entity);
    }
    public void deleteById(Long id) {
        Loan loan = entityManager.find(Loan.class, id);
        if (loan != null) {
            entityManager.remove(loan);
        }
    }

    public Loan findActiveLoanByBookId(UUID bookId) {
        return entityManager.createQuery("SELECT l FROM Loan l WHERE l.bookId = :bookId AND l.returnDate IS NULL", Loan.class)
                .setParameter("bookId", bookId)
                .getSingleResult();
    }
}
