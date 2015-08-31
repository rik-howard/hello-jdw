
package hello.dropwizard.common.types.fields;

import java.util.Objects;

public class PersonId {

    Integer value;

    public PersonId (Integer value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return value.toString ();
    }

    @Override
    public boolean equals (Object object) {
        return
            object != null
            && object.getClass ().equals (getClass ())
            && Objects.equals (this.value, ((PersonId) object).value);
    }

    @Override
    public int hashCode () {
        return Objects.hash (value);
    }

    public Integer value () {
        return value;
    }

}
