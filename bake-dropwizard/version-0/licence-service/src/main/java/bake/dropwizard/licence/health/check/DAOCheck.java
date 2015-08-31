
package bake.dropwizard.licence.health.check;

import bake.dropwizard.licence.DAO;
import com.codahale.metrics.health.HealthCheck;

public class DAOCheck
extends HealthCheck {

    private final DAO licenceDAO;

    public DAOCheck (DAO licenceDAO) {
        this.licenceDAO = licenceDAO;
    }

    @Override
    protected Result check ()
    throws Exception {
        if (licenceDAO.isConnected ())
            return Result.healthy ();
        else
            return Result.unhealthy ("licenceDAO is not connected");
    }

}
