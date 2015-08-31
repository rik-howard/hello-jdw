
package hello.dropwizard.common.types.fields;

import java.util.Objects;

public class Forename {

    private String string;

    public Forename (String string) {
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
        && string.equals (((Forename) obj).string);
    }

}
