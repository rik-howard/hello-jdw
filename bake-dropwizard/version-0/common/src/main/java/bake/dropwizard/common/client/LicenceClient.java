
package bake.dropwizard.common.client;

import bake.dropwizard.common.pojo.Licence;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

public class LicenceClient {

    private Client client;
    private String prefix;
    private String current;

    public LicenceClient (Client client) {
        this.prefix = "http://localhost:8080/";
        this.client = client;
    }

    public String getCurrent () {
        return current;
    }

    public Licence licence (Integer id) {
        String path = "licence-by/id?";
        String query = "id=%s";
        String target = prefix + path + String.format (query, id.toString ());
        this.current = target;
        return client
            .target (target)
            .request (MediaType.APPLICATION_JSON)
            .get (Licence.class);
    }

}
