
package hello.dropwizard.common.types.fields;

public class Surname {

    private String string;

    public Surname (String string) {
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
        && string.equals (((Surname) obj).string);
    }
}
