
package bake.dropwizard;

import io.dropwizard.Configuration;

import java.util.Map;

public class CompanyConfiguration
extends Configuration {

    private Map <String, String> daoConfiguration;

    public CompanyConfiguration () {}

    public CompanyConfiguration (Map <String, String> daoConfiguration) {
        this.daoConfiguration = daoConfiguration;
    }

    public Map <String, String> getDaoConfiguration () {
        return daoConfiguration;
    }

}
