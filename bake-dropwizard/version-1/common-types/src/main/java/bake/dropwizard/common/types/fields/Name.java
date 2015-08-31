
package bake.dropwizard.common.types.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {

    private String string;

    public Name () {}

    public Name (String string) {
        this.string = string;
    }

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
        && string.equals (((Name) obj).string);
    }

    @JsonProperty ("string")
    public String string () {
        return string;
    }

}
