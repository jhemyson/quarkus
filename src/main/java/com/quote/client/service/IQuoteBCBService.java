package com.quote.client.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="quote-api")
public interface IQuoteBCBService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    Object findDollarQuoteBCBByDate(@QueryParam("@dataCotacao") String data);
}