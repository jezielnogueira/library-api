package org.v2com.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.jboss.resteasy.reactive.RestResponse;
import org.v2com.entities.Loan;
import org.v2com.services.LoanService;

import java.util.UUID;

@Path( "/api/v1/loan")
public class LoanController {

    @Inject
    LoanService loanService;

    @POST
    @Path("/loan")
    public RestResponse<Loan> loanBook(@QueryParam("bookId") UUID bookId, @QueryParam("userId") UUID userId) throws Exception {
        Loan loan = loanService.loanBook(bookId, userId);
        return RestResponse.ok(loan);
    }

    @POST
    @Path("/return")
    public RestResponse<Loan> returnBook(@QueryParam("bookId") UUID bookId) throws Exception {
        loanService.returnBook(bookId);
        return RestResponse.ok();
    }

    @POST
    @Path("/renew/{loanId}")
    public RestResponse<Loan> renewBook(@QueryParam("bookId") UUID loanId) {
        Loan loan = loanService.renewBookLoan(loanId, 14);

        return RestResponse.ok(loan);
    }
}
