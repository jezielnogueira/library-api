package org.v2com.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.v2com.entities.Book;
import org.v2com.entities.Loan;
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
    public Loan loanBook(UUID bookId, UUID userId) throws Exception {
        // Verificar se o livro existe
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new Exception("Livro não encontrado");
        }

        // Verificar se o livro já está emprestado
        Loan existingLoan = loanRepository.findActiveLoanByBookId(bookId);
        if (existingLoan != null) {
            throw new Exception("Livro já está emprestado");
        }

        // Criar e persistir o empréstimo
        Loan loan = new Loan();
        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null); // Não há data de devolução ainda

        loanRepository.persist(loan);

        return loan;
    }

    @Transactional
    public void returnBook(UUID loanId) throws Exception {
        // Buscar o empréstimo ativo
        Loan loan = loanRepository.findById(loanId);
        if (loan == null) {
            throw new Exception("Empréstimo não encontrado");
        }

        // Atualizar data de devolução
        loan.setReturnDate(LocalDate.now());
        loanRepository.persist(loan);
    }

    public Loan renewBookLoan(UUID laonId, int days) {
        Loan loan = loanRepository.findById(laonId);
        loan.setReturnDate(LocalDate.now().plusDays(days));
        loanRepository.persist(loan);
        return loan;
    }
    public Loan findLoanById(UUID loanId) {
        return loanRepository.findById(loanId);
    }


}
