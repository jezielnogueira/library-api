package org.v2com.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import java.util.UUID;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.BookEntity;
import org.v2com.entities.LoanEntity;
import org.v2com.services.BookService;
import org.v2com.services.LoanService;

import java.util.List;

@Path("/api/v1/books")
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GET
    public RestResponse<List<BookEntity>> getAllBooks(){
        List<BookEntity> bookEntities = service.findAllBooks();
        return bookEntities != null && !bookEntities.isEmpty()
                ? RestResponse.ok(bookEntities)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @GET
    @Path("/{id}")
    public RestResponse<BookEntity> getBookById(@PathParam("id") String idStr){
        BookEntity bookEntity = service.findBookById(UUID.fromString(idStr));
        return bookEntity != null
                ? RestResponse.ok(bookEntity)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @GET
    @Path("/search")
    public RestResponse<List<BookEntity>> getBooksByArgs(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("tag") String tag){
        List<BookEntity> bookEntities = service.searchBooksByArgs(title, author, tag);
        return bookEntities != null && !bookEntities.isEmpty()
                ? RestResponse.ok(bookEntities)
                : RestResponse.status(RestResponse.Status.NO_CONTENT);
    }

    @POST
    @Path("/")
    public RestResponse<Void> persistBook(@Valid BookEntity bookEntity, @Context UriInfo uriInfo) {
        BookEntity persistedBookEntity = service.persistBook(bookEntity);
        var location = uriInfo.getAbsolutePathBuilder().path(persistedBookEntity.id.toString()).build();
        return RestResponse.created(location);
    }

    @PUT
    @Path("/")
    public RestResponse<BookEntity> updateBook(@Valid BookEntity bookEntity){
        bookEntity = service.updateBook(bookEntity.id, bookEntity);
        return RestResponse.ok(bookEntity);
    }

    @DELETE
    @Path("/")
    public RestResponse<Void> deleteBook(@QueryParam("id") String idStr) {

        service.deleteBook(UUID.fromString(idStr));
        return RestResponse.noContent();
    }

}