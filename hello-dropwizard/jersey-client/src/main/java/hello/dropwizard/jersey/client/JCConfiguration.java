
package hello.dropwizard.jersey.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JCConfiguration
extends Configuration {

    @Valid @NotNull private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration ();

    @JsonProperty ("jerseyClientConfiguration")
    public JerseyClientConfiguration getJerseyClientConfiguration () {
        return jerseyClientConfiguration;
    }

}
