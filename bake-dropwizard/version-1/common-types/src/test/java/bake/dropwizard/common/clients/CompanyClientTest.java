
package bake.dropwizard.common.clients;

import bake.dropwizard.common.types.pojos.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyClientTest {

    CompanyClient testee;
    List <Company> companys;
    Company company;
    Client client;
    WebTarget webTarget;
    Builder builder;

    @Before
    public void setUp ()
    throws Exception {
        builder = mock (Builder.class);
        webTarget = mock (WebTarget.class);
        client = mock (Client.class);
        company = mock (Company.class);
        companys = new ArrayList <Company> () {{add (company);}};
        testee = new CompanyClient (client);
        when (webTarget.request (MediaType.APPLICATION_JSON)).thenReturn (builder);
        when (builder.get (new GenericType <List <Company>> (Company.class))).thenReturn (companys);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGottenCompanys ()
    throws Exception {
        when (client.target (CompanyClient.accession)).thenReturn (webTarget);
        List <Company> expected = companys;
        List <Company> actual = testee.gottenCompanys (Company.company (null, null, null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenCompanysById ()
    throws Exception {
        when (client.target (CompanyClient.accession + "?id=1")).thenReturn (webTarget);
        List <Company> expected = companys;
        List <Company> actual = testee.gottenCompanys (Company.company (1, null, null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenCompanysByName ()
    throws Exception {
        when (client.target (CompanyClient.accession + "?name=the+Experience")).thenReturn (webTarget);
        List <Company> expected = companys;
        List <Company> actual = testee.gottenCompanys (Company.company (null, "the Experience", null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testPostedCompany ()
    throws Exception {
        when (builder.post (Entity.entity (company, MediaType.APPLICATION_JSON), Company.class)).thenReturn (company);
        when (client.target (CompanyClient.mutation)).thenReturn (webTarget);
        Company expected = company;
        Company actual = testee.postedCompany (company);
        assert expected.equals (actual);
    }

    @Test
    public void testPutCompany ()
    throws Exception {
        when (builder.put (Entity.entity (company, MediaType.APPLICATION_JSON), Company.class)).thenReturn (company);
        when (client.target (CompanyClient.mutation)).thenReturn (webTarget);
        Company expected = company;
        Company actual = testee.putCompany (company);
        assert expected.equals (actual);
    }

    @Test
    public void testDeletedCompany ()
    throws Exception {
        when (builder.delete (Company.class)).thenReturn (company);
        when (client.target (CompanyClient.mutation + "?id=1")).thenReturn (webTarget);
        Company expected = company;
        Company actual = testee.deletedCompany (1);
        assert expected.equals (actual);
    }

}
