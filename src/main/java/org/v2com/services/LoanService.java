package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.v2com.entities.BookEntity;
import org.v2com.entities.LoanEntity;
import org.v2com.repositories.BookRepository;
import org.v2com.repositories.LoanRepository;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class LoanService {

    @Inject
    BookRepository bookRepository;

    @Inject
    LoanRepository loanRepository;

    @Transactional
    public LoanEntity loanBook(UUID bookId, UUID userId) throws Exception {
        // Verificar se o livro existe
        BookEntity bookEntity = bookRepository.findById(bookId);
        if (bookEntity == null) {
            throw new Exception("Livro não encontrado");
        }

        // Verificar se o livro já está emprestado
        LoanEntity existingLoanEntity = loanRepository.findActiveLoanByBookId(bookId);
        if (existingLoanEntity != null) {
            throw new Exception("Livro já está emprestado");
        }

        // Criar e persistir o empréstimo
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBookId(bookId);
        loanEntity.setUserId(userId);
        loanEntity.setLoanDate(LocalDate.now());
        loanEntity.setReturnDate(null); // Não há data de devolução ainda

        loanRepository.persist(loanEntity);

        return loanEntity;
    }

    @Transactional
    public void returnBook(UUID loanId) throws Exception {
        // Buscar o empréstimo ativo
        LoanEntity loanEntity = loanRepository.findById(loanId);
        if (loanEntity == null) {
            throw new Exception("Empréstimo não encontrado");
        }

        // Atualizar data de devolução
        loanEntity.setReturnDate(LocalDate.now());
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
