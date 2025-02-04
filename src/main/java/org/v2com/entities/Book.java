package org.v2com.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
public class Book extends PanacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Usando AUTO para UUID
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

    public String isbn;
    public String genre;

    public String cover;
    public String status;
    public String language;
    public String publisher;
    public String tags;
    public String coverUrl;

}
