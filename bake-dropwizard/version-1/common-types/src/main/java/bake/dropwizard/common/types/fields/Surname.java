
package bake.dropwizard.common.types.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Surname {

    private String string;

    public Surname (String string) {
        this.string = string;
    }

    public Surname () {}

    @Override
    public String toString () {
        return string;
    }

    @Override
    public int hashCode () {
        return string.hashCode ();
    }

    @Override
    public boolean equals (Object obj) {
        return obj != null
        && getClass ().equals (obj.getClass ())
        && string.equals (((Surname) obj).string);
    }

    @JsonProperty ("string")
    public String string () {
        return string;
    }

}
