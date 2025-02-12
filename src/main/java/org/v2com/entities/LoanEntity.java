package org.v2com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class LoanEntity extends PanacheEntityBase {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    public UUID id;

    @Column(nullable = false)
    private UUID bookId;

//    @Column(nullable = false)
//    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private boolean returned;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public UUID getBookId() { return this.bookId; }

    public void setBookId(UUID bookId) { this.bookId = bookId; }

    public boolean isReturned() { return returned; }

    public void setReturned(boolean returned) { this.returned = returned; }

    //public UUID getUserId() { return this.userId; }

    //public void setUserId(UUID userId) { this.userId = userId; }

    public UserEntity getUser() { return this.user; }

    public void setUser(UserEntity user) { this.user = user; }

    public LocalDate getLoanDate() { return this.loanDate; }

    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
