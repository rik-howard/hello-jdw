
package bake.dropwizard.company.person.health.check;

import bake.dropwizard.company.person.DAO;
import com.codahale.metrics.health.HealthCheck;

public class DAOCheck
extends HealthCheck {

    private final bake.dropwizard.company.person.DAO DAO;

    public DAOCheck (DAO DAO) {
        this.DAO = DAO;
    }

    @Override
    protected Result check ()
    throws Exception {
        if (DAO.isConnected ())
            return Result.healthy ();
        else
            return Result.unhealthy ("DAO is not connected");
    }

}
