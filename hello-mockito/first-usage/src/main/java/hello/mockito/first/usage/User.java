
package hello.mockito.first.usage;

public class User {

    private Integer id;
    private String name;

    public User (Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id () {
        return id;
    }

    public String name () {
        return name;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null)
            return false;
        else if (this.getClass () == object.getClass ()) {
            User that = (User) object;
            return this.id ().equals (that.id ())
            && this.name ().equals (that.name ());
        }
        else
            return false;
    }

    @Override
    public int hashCode () {
        return 31 * id.hashCode ()+ name.hashCode ();
    }

    @Override
    public String toString () {
        return String.format ("User {id: %s, name: '%s'}", id, name);
    }

}
