
package hello.dropwizard.datastax.cassandra;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import java.util.Map;

public class DCConfiguration
extends Configuration {

    private Map <String, String> databaseConfiguration;

    public DCConfiguration () {}

    public DCConfiguration (Map <String, String> databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @JsonProperty
    public Map<String, String> getDatabaseConfiguration () {
        return databaseConfiguration;
    }

}
