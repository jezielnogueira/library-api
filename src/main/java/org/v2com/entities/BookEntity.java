package org.v2com.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.v2com.enuns.BookStatus;

import java.util.UUID;

@Entity
public class BookEntity extends PanacheEntityBase {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    public UUID id;

    @NotNull
    @Size(min = 3, max = 100)
    public String title;

    @NotNull
    @Size(min = 3, max = 100)
    public String author;

    @NotNull
    @Size(min = 3, max = 100)
    public String description;



    public String genre;

    public String isbn;

    public String cover;

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Column(name = "status", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    public BookStatus status;

    public String publisher;
    public String tags;
    public String coverUrl;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
