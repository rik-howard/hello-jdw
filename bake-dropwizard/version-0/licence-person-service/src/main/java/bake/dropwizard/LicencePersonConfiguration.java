
package bake.dropwizard;

import bake.dropwizard.licence.person.DAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LicencePersonConfiguration
extends Configuration {

    @Valid @NotNull private DAO dao = new DAO ();
    @Valid @NotNull private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration ();

    @JsonProperty ("dao")
    public DAO getDAO () {
        return dao;
    }

    @JsonProperty ("jerseyClientConfiguration")
    public JerseyClientConfiguration getJerseyClientConfiguration () {
        return jerseyClientConfiguration;
    }

}
