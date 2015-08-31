
package bake.dropwizard.common.clients;

import bake.dropwizard.common.types.pojos.Company;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class CompanyClient {

    public static final String accession = "http://localhost:10002/companys";
    public static final String mutation = "http://localhost:10002/company";
    private Client client;

    public CompanyClient (Client client) {
        this.client = client;
    }

    public List <Company> gottenCompanys (Company company) {
        return
            company.queriesById ()?
                gottenCompanys (queryById (company)):
            company.queriesByName ()?
                gottenCompanys (queryByName (company)):
            gottenCompanys ();
    }

    private List <Company> gottenCompanys (String query) {
        String target = accession + query;
        return client
            .target (target)
            .request (MediaType.APPLICATION_JSON)
            .get (new GenericType <List <Company>> (Company.class));
    }

    private String queryById (Company company) {
        return String.format ("?id=%s", company.getId ());
    }

    private String queryByName (Company company) {
        return String.format ("?name=%s", company.getName ().string ().replaceAll (" ", "+"));
    }

    private List <Company> gottenCompanys () {
        String target = accession;
        return client
        .target (target)
        .request (MediaType.APPLICATION_JSON)
        .get (new GenericType <List <Company>> (Company.class));
    }

    public Company postedCompany (Company company) {
        return client
            .target (mutation)
            .request (MediaType.APPLICATION_JSON)
            .post (Entity.entity (company, MediaType.APPLICATION_JSON), Company.class);
    }

    public Company putCompany (Company company) {
        return client
            .target (mutation)
            .request (MediaType.APPLICATION_JSON)
            .put (Entity.entity (company, MediaType.APPLICATION_JSON), Company.class);
    }

    public Company deletedCompany (Integer id) {
        return client
            .target (mutation + "?id=" + id)
            .request (MediaType.APPLICATION_JSON)
            .delete (Company.class);
    }

}
