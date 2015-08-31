
package bake.dropwizard;

import bake.dropwizard.company.person.DAO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CompanyPersonConfiguration
extends Configuration {

    @Valid @NotNull private DAO dao = new DAO ();

    @JsonProperty ("dao")
    public DAO getDAO () {
        return dao;
    }

}
