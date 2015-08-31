
package hello.dropwizard.resource.methods;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RMApplication
extends Application <RMConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <RMConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (RMConfiguration rmConfiguration, Environment environment)
    throws Exception {
        environment.jersey ().register (
            new UserResource (
                new UserService (
                    rmConfiguration.getUserDAO ()
                )
            )
        );
    }

    public static void main (String [] args)
    throws Exception {
        new RMApplication ().run (args);
    }

}
