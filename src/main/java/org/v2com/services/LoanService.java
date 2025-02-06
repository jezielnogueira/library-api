package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.v2com.entities.BookEntity;
import org.v2com.entities.LoanEntity;
import org.v2com.entities.UserEntity;
import org.v2com.enuns.BookStatus;
import org.v2com.enuns.UserStatus;
import org.v2com.repositories.BookRepository;
import org.v2com.repositories.LoanRepository;
import org.v2com.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LoanService {

    @Inject
    BookRepository bookRepository;

    @Inject
    LoanRepository loanRepository;

    @Inject
    UserRepository userRepository;


    public List<LoanEntity> getAllLoans() {
        return LoanEntity.listAll();
    }

    @Transactional
    public LoanEntity loanBook(UUID bookId, UUID userId) throws Exception {
        BookEntity bookEntity = bookRepository.findById(bookId);
        LoanEntity existingLoanEntity = loanRepository.findActiveLoanByBookId(bookId);
        UserEntity userEntity = userRepository.findbyId(userId);

        if (bookEntity == null) {
            throw new Exception("Livro não encontrado");
        }
        if (existingLoanEntity != null && !existingLoanEntity.isReturned()) {
            throw new Exception("Livro já está emprestado");
        }
        if (bookEntity.getStatus() == BookStatus.UNAVAILABLE) {
            throw new Exception("Livro indisponivel");
        }
        if (userEntity == null) {
            throw new Exception("Usuário não encontrado");
        }
        if (userEntity.getStatus() == UserStatus.INACTIVE) {
            throw new Exception("Usuario inativo");
        }

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBookId(bookId);
        loanEntity.setReturned(false);
        loanEntity.setLoanDate(LocalDate.now());
        loanEntity.setReturnDate(LocalDate.now().plusDays(14));
        loanEntity.setUser(userEntity);

        bookRepository.changeBookStatus(bookEntity, BookStatus.UNAVAILABLE);
        loanRepository.persist(loanEntity);

        return loanEntity;
    }

    @Transactional
    public void returnBook(UUID loanId) throws Exception {
        LoanEntity loanEntity = loanRepository.findById(loanId);
        if (loanEntity == null) {
            throw new Exception("Empréstimo não encontrado");
        }

        loanRepository.changeLoanStatus(loanEntity, true);

        loanRepository.persist(loanEntity);
    }

    public LoanEntity renewBookLoan(UUID laonId, int days) {
        LoanEntity loanEntity = loanRepository.findById(laonId);
        loanEntity.setReturnDate(LocalDate.now().plusDays(days));
        loanRepository.persist(loanEntity);
        return loanEntity;
    }

    public LoanEntity findLoanById(UUID loanId) {
        return loanRepository.findById(loanId);
    }

}
