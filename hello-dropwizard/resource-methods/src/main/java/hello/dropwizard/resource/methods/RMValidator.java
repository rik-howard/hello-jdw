
package hello.dropwizard.resource.methods;

import javax.validation.constraints.NotNull;

public class RMValidator {

    @NotNull
    public static Boolean validatesId (Integer id) {
        return
            id != null
            && id >= 0;
    }

    @NotNull
    public static Boolean validatesUser (User user) {
        return
            user != null
            && user.getId () > 0
            && ! user.getName ().equals ("");
    }

    @NotNull
    public static Boolean validatesIdAndUser (Integer id, User user) {
        return
            validatesId (id)
            && validatesUser (user)
            && id.equals (user.getId ());
    }

}
