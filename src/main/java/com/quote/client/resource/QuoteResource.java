package com.quote.client.resource;

import com.quote.client.entity.QuoteEntity;
import com.quote.client.service.QuoteService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedHashMap;
import java.util.List;

@Path("/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteResource {

    @Inject
    QuoteService quoteService;

    @GET

    @Path("/dollar/date/{date}")
    @Transactional
    public List<LinkedHashMap> hello(@PathParam("date") String date) {

        List<LinkedHashMap> quoteList = quoteService.findDollarByDate(date);

        quoteService.persistData(quoteList);

        return quoteList;
    }

    @GET
    @Transactional
    public List<QuoteEntity> findAll() {
        return quoteService.findAll();
    }



}