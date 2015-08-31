
package hello.dropwizard.jersey.client;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;

@Path ("/jersey-client")
public class JCResource {

    private Client client;

    public JCResource () {}

    public JCResource (Client client) {
        this.client = client;
    }

    @GET @Timed
    public String get () {
        return "<pre>"
            + client
                .target ("http://localhost:8080/getting-started")
                .request ()
                .get (JCPOJO.class)
                .toString ()
            + "</pre>";
    }

}
