package org.v2com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.LoanEntity;
import org.v2com.services.LoanService;

import java.util.List;
import java.util.UUID;

@Path( "/api/v1/loan")
public class LoanController {

    @Inject
    LoanService loanService;

    @GET
    @Path("/list")
    public RestResponse<List<LoanEntity>> getAllLoan() {
        List<LoanEntity> loanEntities = loanService.getAllLoans();
        return loanEntities != null && !loanEntities.isEmpty()
                ? RestResponse.ok(loanEntities)
                : RestResponse.status(RestResponse.Status.NOT_FOUND);

    }

    @GET
    @Path("/byloan/{id}")
    public RestResponse<LoanEntity> getLoanById(@PathParam("id") UUID loanId) {
        LoanEntity loanEntity = loanService.findLoanById(loanId);
        return RestResponse.ok(loanEntity);
    }

    @GET
    @Path("/byuser/{id}")
    public RestResponse<List<LoanEntity>> getLoanByUserId(@PathParam("id") UUID id) {
        return null;
    }

    @POST
    @Path("/create")
    public RestResponse<LoanEntity> loanBook(@QueryParam("bookId") UUID bookId, @QueryParam("userId") UUID userId) throws Exception {
        LoanEntity loanEntity = loanService.loanBook(bookId, userId);
        return RestResponse.ok(loanEntity);
    }

    @POST
    @Path("/return")
    public RestResponse<LoanEntity> returnBook(@QueryParam("bookId") UUID bookId) throws Exception {
        loanService.returnBook(bookId);
        return RestResponse.ok();
    }

    @POST
    @Path("/renew/{loanId}")
    public RestResponse<LoanEntity> renewBook(@QueryParam("bookId") UUID loanId) {
        LoanEntity loanEntity = loanService.renewBookLoan(loanId, 14);

        return RestResponse.ok(loanEntity);
    }
}