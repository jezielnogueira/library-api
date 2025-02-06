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

    public UUID getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getTags() { return tags; }

    public void setTags(String tags) { this.tags = tags; }

    public String getCoverUrl() { return coverUrl; }

    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    public BookStatus getStatus() { return status; }

    public void setStatus(BookStatus status) { this.status = status; }
}
