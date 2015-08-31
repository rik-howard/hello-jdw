
package bake.dropwizard;

import bake.dropwizard.licence.DAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LicenceConfiguration
extends Configuration {

    @Valid @NotNull private DAO dao = new DAO ();

    @JsonProperty ("dao")
    public DAO getDAO () {
        return dao;
    }

}
