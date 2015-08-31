
package hello.dropwizard.resource.methods;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull private Integer id;
    @NotNull private String name;

    public User () {}

    public User (Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    @Override
    public String toString () {
        return String.format ("User {id: {}, name: '{}'}", id, name);
    }

}
