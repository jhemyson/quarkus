package com.quote.client.service;

import com.quote.client.entity.QuoteEntity;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;

@ApplicationScoped
public class QuoteService {

    @Inject
    @RestClient
    IQuoteBCBService IQuoteBCBService;

    public List<LinkedHashMap> findDollarByDate(String date) {
        Object quoteResponse = IQuoteBCBService.findDollarQuoteBCBByDate("'" + date + "'");
        List<LinkedHashMap> quoteList = (List<LinkedHashMap>) ((LinkedHashMap) quoteResponse).get("value");

        return quoteList;
    }

    public void persistData (List<LinkedHashMap> quoteList) {
        if(!quoteList.isEmpty()) {

            quoteList.forEach(linkedHashMap -> {
                QuoteEntity quoteExists = QuoteEntity.find("quote_date_time",
                        (String) linkedHashMap.get("dataHoraCotacao")).firstResult();

                if(quoteExists == null) {
                    QuoteEntity quoteEntity = new QuoteEntity();
                    quoteEntity.purchase = ((Double) linkedHashMap.get("cotacaoCompra"));
                    quoteEntity.sale = ((Double) linkedHashMap.get("cotacaoVenda"));
                    quoteEntity.quoteDateTime = ((String) linkedHashMap.get("dataHoraCotacao"));
                    quoteEntity.persist();
                }
            });
        }
    }

    public List<QuoteEntity> findAll() {
        return QuoteEntity.listAll();
    }

}
