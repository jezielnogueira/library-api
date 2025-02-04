package org.v2com.controller;


import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.Book;
import org.v2com.services.BookService;

import java.util.List;

@Path("/api/v1/book")
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
    @Path("/findbook/{id}")
    public RestResponse<Book> getBook(@RestPath long id){
        Book book = service.findBookById(id);
        return book != null
                ? RestResponse.ok(book)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);
    }

    @POST
    public RestResponse<Void> persistBook(@Valid Book book, @Context UriInfo uriInfo) {
        Book persistedBook = service.persistBook(book);
        var location = uriInfo.getAbsolutePathBuilder().path(persistedBook.id.toString()).build();
        return RestResponse.created(location);
    }




}