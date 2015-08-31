
package hello.dropwizard.jersey.client;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

public class JCApplication
extends Application <JCConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <JCConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (JCConfiguration jcConfiguration, Environment environment)
    throws Exception {
        final Client client = new JerseyClientBuilder (environment)
            .using (jcConfiguration.getJerseyClientConfiguration ())
            .build (getName ());
        final JCResource jcResource = new JCResource (client);
        environment.jersey ().register (jcResource);
    }

    public static void main (String [] args)
    throws Exception{
        new JCApplication ().run (args);
    }

}
