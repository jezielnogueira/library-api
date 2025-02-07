package org.v2com.controller;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.BookEntity;
import org.v2com.entities.LoanEntity;
import org.v2com.services.BookService;
import org.v2com.services.LoanService;

import java.util.List;

@Path("/api/v1/books")
public class BookController {

    @Inject
    LoanService loanService;

    @GET
    @Path("/list")
    public RestResponse<List<LoanEntity>> getAllLoan() {
        List<LoanEntity> loanEntities = loanService.getAllLoans();
        return loanEntities != null && !loanEntities.isEmpty()
                ? RestResponse.ok(loanEntities)
                : RestResponse.status(RestResponse.Status.NO_CONTENT);
    }

    @GET
    @Path("/loanbyid/{id}")
    public RestResponse<LoanEntity> getLoanById(@PathParam("id") UUID loanId) {
        LoanEntity loanEntity = loanService.findLoanById(loanId);
        return RestResponse.ok(loanEntity);
    }

    @GET
    @Path("/getbyuserid/{id}")
    public RestResponse<List<LoanEntity>> getLoanByUserId(@PathParam("id") UUID id) {
        List<LoanEntity> loanEntities = loanService.findActiveLoanByUserId(id);
        return loanEntities != null && !loanEntities.isEmpty()
                ? RestResponse.ok(loanEntities)
                : RestResponse.status(RestResponse.Status.NO_CONTENT);
    }

    @POST
    @Path("/create")
    public RestResponse<LoanEntity> loanBook(@QueryParam("bookId") UUID bookId, @QueryParam("userId") UUID userId) throws Exception {
        LoanEntity loanEntity = loanService.loanBook(bookId, userId);
        return RestResponse.ok(loanEntity);
    }

    @PUT
    @Path("/returnbook")
    public RestResponse<LoanEntity> returnBook(@QueryParam("loanId") UUID loanId) throws Exception {
        loanService.endLoan(loanId);
        return RestResponse.ok();
    }

    @PUT
    @Path("/renew/{loanId}")
    public RestResponse<LoanEntity> renewBook(@PathParam("loanId") UUID loanId) {
        LoanEntity loanEntity = loanService.renewBookLoan(loanId, 14);

        return RestResponse.ok(loanEntity);
    }
}