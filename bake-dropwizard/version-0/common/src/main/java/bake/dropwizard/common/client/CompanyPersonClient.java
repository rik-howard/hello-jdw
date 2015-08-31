
package bake.dropwizard.common.client;

import bake.dropwizard.common.pojo.CompanyPerson;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class CompanyPersonClient {

    private Client client;
    private String prefix;
    private String current;

    public CompanyPersonClient (Client client) {
        this.prefix = "http://localhost:8082/";
        this.client = client;
    }

    public String getCurrent () {
        return current;
    }

    public CompanyPerson companyPerson (Integer companyId, Integer personId) {
        String path ="company-person-by/company-id/person-id?";
        String query ="companyId=%s&personId=%s";
        String target =  prefix + path + String.format (query, companyId, personId);
        this.current = target;
        return client
            .target (target)
            .request (MediaType.APPLICATION_JSON)
            .get (CompanyPerson.class);
    }

}
