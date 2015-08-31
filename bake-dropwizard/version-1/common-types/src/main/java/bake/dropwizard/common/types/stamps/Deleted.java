
package bake.dropwizard.common.types.stamps;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

public class Deleted
implements Serializable {

    private DateTime dateTime;

    public Deleted () {}

    public Deleted (DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals (Object object) {
        return object != null
        && object.getClass ().equals (getClass ())
        && Objects.equals (this.dateTime, ((Deleted) object).dateTime);
    }

    @Override
    public int hashCode () {
        return Objects.hash (dateTime);
    }

    @Override
    public String toString () {
        return dateTime.toString ();
    }

    @JsonProperty ("date-time")
    public DateTime dateTime () {
        return dateTime;
    }

}
