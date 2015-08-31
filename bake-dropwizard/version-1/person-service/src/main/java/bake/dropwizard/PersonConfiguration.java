
package bake.dropwizard;

import io.dropwizard.Configuration;

import java.util.Map;

public class PersonConfiguration
extends Configuration {

    private Map <String, String> daoConfiguration;

    public PersonConfiguration () {}

    public PersonConfiguration (Map <String, String> daoConfiguration) {
        this.daoConfiguration = daoConfiguration;
    }

    public Map <String, String> getDaoConfiguration () {
        return daoConfiguration;
    }

}
