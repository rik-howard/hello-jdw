
package hello.dropwizard.jdbi.mysql;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class JMApplication
extends Application <JMConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <JMConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (JMConfiguration jmConfiguration, Environment environment)
    throws Exception {
        environment.jersey ().register (
            new JMResource (
                new DBIFactory ()
                    .build (environment, jmConfiguration.getDataSourceFactory (), "mysql")
                    .onDemand (JMDAO.class)
            )
        );
    }

    public static void main (String [] args)
    throws Exception {
        new JMApplication ().run (args);
    }

}
