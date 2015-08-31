
package hello.dropwizard.getting.started;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class GSConfiguration
extends Configuration {

    @NotEmpty private String template;
    @NotEmpty private String defaultName = "Stranger";

    @JsonProperty public String getTemplate () {
        return template;
    }

    @JsonProperty public String getDefaultName () {
        return defaultName;
    }

    @JsonProperty public void setTemplate (String template) {
        this.template = template;
    }

    @JsonProperty public void setDefaultName (String name) {
        this.defaultName = name;
    }

}
