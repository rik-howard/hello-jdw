
package bake.dropwizard.licence.person.health.check;

import bake.dropwizard.licence.person.DAO;
import com.codahale.metrics.health.HealthCheck;

public class DAOCheck
extends HealthCheck {

    private final DAO licencePersonDAO;

    public DAOCheck (DAO licencePersonDAO) {
        this.licencePersonDAO = licencePersonDAO;
    }

    @Override
    protected Result check ()
    throws Exception {
        if (licencePersonDAO.isConnected ())
            return Result.healthy ();
        else
            return Result.unhealthy ("licencePersonDAO is not connected");
    }

}
