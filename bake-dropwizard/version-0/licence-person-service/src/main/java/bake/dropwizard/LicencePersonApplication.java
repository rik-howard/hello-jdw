
package bake.dropwizard;

import bake.dropwizard.common.client.CompanyPersonClient;
import bake.dropwizard.common.client.LicenceClient;
import bake.dropwizard.licence.person.health.check.DAOCheck;
import bake.dropwizard.licence.person.Resource;
import bake.dropwizard.licence.person.Responder;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class LicencePersonApplication
extends Application <LicencePersonConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <LicencePersonConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (LicencePersonConfiguration configuration, Environment environment)
    throws Exception {

        Client client = new JerseyClientBuilder (environment)
            .using (configuration.getJerseyClientConfiguration ())
            .build (getName ());

        final DAOCheck daoCheck = new DAOCheck (configuration.getDAO ());
        final Resource resource = new Resource (
            new Responder (
                configuration.getDAO (),
                new LicenceClient (client),
                new CompanyPersonClient (client)
            )
        );

        environment.healthChecks ().register ("daoCheck", daoCheck);
        environment.jersey ().register (resource);

    }

    public static void main (String [] args)
    throws Exception {
        new LicencePersonApplication ().run (args);
    }

}
