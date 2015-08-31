
package hello.dropwizard.resource.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RMConfiguration
extends Configuration {

    @Valid @NotNull private UserDAO userDAO = new UserDAO ();

    @JsonProperty ("userDAO")
    public  UserDAO getUserDAO () {
        return  userDAO;
    }

}
