
package hello.dropwizard.date.range;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DRApplication
extends Application <DRConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <DRConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (DRConfiguration configuration, Environment environment)
    throws Exception {
        final DAO dao = new DAO ();
        final Responder responder = new Responder (dao);
        final Resource resource = new Resource (responder);
        environment.jersey ().register (resource);
    }

    public static void main (String [] args)
    throws Exception {
        new DRApplication ().run (args);
    }

}
