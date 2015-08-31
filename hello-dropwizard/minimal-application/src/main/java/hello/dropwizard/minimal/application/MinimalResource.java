
package hello.dropwizard.minimal.application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("/")
public class MinimalResource {

    @GET public String value () {
        return "the value string from the minimal application";
    }

}
