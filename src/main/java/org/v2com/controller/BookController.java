package org.v2com.controller;


import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.Book;
import org.v2com.services.BookService;

import java.util.List;

@Path("/api/v1/books")
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GET
    public RestResponse<List<Book>> getAllBooks(){
        List<Book> books = service.findAllBooks();
        return books != null && !books.isEmpty()
                ? RestResponse.ok(books)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @GET
    @Path("/{id}")
    public RestResponse<Book> getBookById(@PathParam("id") String idStr){
        Book book = service.findBookById(UUID.fromString(idStr));
        return book != null
                ? RestResponse.ok(book)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @GET
    @Path("/search")
    public RestResponse<List<Book>> getBooksByArgs(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("tag") String tag){
        List<Book> books = service.searchBooksByArgs(title, author, tag);
        return books != null && !books.isEmpty()
                ? RestResponse.ok(books)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @POST
    @Path("/")
    public RestResponse<Void> persistBook(@Valid Book book, @Context UriInfo uriInfo) {
        Book persistedBook = service.persistBook(book);
        var location = uriInfo.getAbsolutePathBuilder().path(persistedBook.id.toString()).build();
        return RestResponse.created(location);
    }

    @PUT
    @Path("/")
    public RestResponse<Book> updateBook(@Valid Book book){
        book = service.updateBook(book.id, book);
        return RestResponse.ok(book);
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Void> deleteBook(@PathParam("id") String idStr) {

        service.deleteBook(UUID.fromString(idStr));
        return RestResponse.noContent();
    }

}