package org.v2com.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.v2com.entities.LoanEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LoanRepository {
    @Inject
    EntityManager entityManager;

    public void persist(Object entity) {
        entityManager.persist(entity);
    }
    public LoanEntity findById(UUID id) {
        return entityManager.find(LoanEntity.class, id);
    }
    public void update(Object entity) {
        entityManager.merge(entity);
    }
    public void deleteById(Long id) {
        LoanEntity loanEntity = entityManager.find(LoanEntity.class, id);
        if (loanEntity != null) {
            entityManager.remove(loanEntity);
        }
    }

    public LoanEntity changeLoanStatus(LoanEntity loanEntity, boolean returned){
        loanEntity = findById(loanEntity.id);
        loanEntity.setReturned(returned);
        entityManager.merge(loanEntity);
        return loanEntity;
    }

    public LoanEntity findActiveLoanByBookId(UUID bookId) {
        List<LoanEntity> result = entityManager.createQuery("SELECT l FROM LoanEntity l WHERE l.bookId = :bookId AND l.returned = false", LoanEntity.class)
                .setParameter("bookId", bookId)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
