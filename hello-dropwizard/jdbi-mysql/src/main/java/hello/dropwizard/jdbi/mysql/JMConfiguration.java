
package hello.dropwizard.jdbi.mysql;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JMConfiguration
extends Configuration {

    @Valid @NotNull private DataSourceFactory dataSourceFactory = new DataSourceFactory ();

    @JsonProperty ("dataSourceFactory")
    public DataSourceFactory getDataSourceFactory () {
        return  dataSourceFactory;
    }

}
