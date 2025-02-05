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

    @POST
    public RestResponse<Void> persistBook(@Valid Book book, @Context UriInfo uriInfo) {
        Book persistedBook = service.persistBook(book);
        var location = uriInfo.getAbsolutePathBuilder().path(persistedBook.id.toString()).build();
        return RestResponse.created(location);
    }

    @PUT
    public Response updateBook(@Valid Book book){
        try {
            Book updatedBook = service.updateBook(book.id, book);
            return Response.ok(updatedBook).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Livro não encontrado para o ID fornecido.")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o livro.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public RestResponse<Void> deleteBook(@PathParam("id") String idStr) {

        service.deleteBook(UUID.fromString(idStr));
        return RestResponse.noContent();
    }

}