
package bake.dropwizard.common.types.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forename {

    private String string;

    public Forename () {}

    public Forename (String string) {
        this.string = string;
    }

    @Override
    public boolean equals (Object obj) {
        return obj != null
        && getClass ().equals (obj.getClass ())
        && string.equals (((Forename) obj).string);
    }

    @Override
    public int hashCode () {
        return string.hashCode ();
    }

    @Override
    public String toString () {
        return string;
    }

    @JsonProperty ("string")
    public String string () {
        return string;
    }

}
