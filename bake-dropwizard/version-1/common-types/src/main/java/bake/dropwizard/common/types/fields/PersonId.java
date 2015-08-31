
package bake.dropwizard.common.types.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class PersonId
implements Serializable {

    private Integer integer;

    public PersonId () {}

    public PersonId (Integer integer) {
        this.integer = integer;
    }

    @Override
    public boolean equals (Object object) {
        return object != null
        && object.getClass ().equals (getClass ())
        && Objects.equals (this.integer, ((PersonId) object).integer);
    }

    @Override
    public int hashCode () {
        return Objects.hash (integer);
    }

    @Override
    public String toString () {
        return integer.toString ();
    }

    @JsonProperty ("integer")
    public Integer integer () {
        return integer;
    }

}
