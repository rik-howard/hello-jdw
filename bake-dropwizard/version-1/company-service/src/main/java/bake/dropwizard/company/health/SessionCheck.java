
package bake.dropwizard.company.health;

import com.codahale.metrics.health.HealthCheck;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class SessionCheck
extends HealthCheck {

    private final Session session;

    public SessionCheck (Session session) {
        this.session = session;
    }

    @Override
    protected Result check ()
    throws Exception {
        try {
            //String statement = "select * from system.schema_keyspaces;";
            String statement = "select * from system.paxos;";
            ResultSet resultSet = session.execute (statement);
            return Result.healthy ();
        }
        catch (Exception e) {
            return Result.unhealthy (e.toString ());
        }
    }

}
