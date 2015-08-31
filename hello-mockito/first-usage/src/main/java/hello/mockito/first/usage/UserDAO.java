
package hello.mockito.first.usage;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private List <User> users = new ArrayList <User> ();

    public void include (User user)
    throws Exception {
        if (listContainsId (user.id ()))
            throw new Exception ("user exists");
        else
            users.add (user);
    }

    public void exclude (Integer id)
    throws Exception {
        User user = user (id);
        if (user == null)
            throw new Exception ("user exists not");
        else
            users.remove (user);
    }

    public void reclude (User user)
    throws Exception {
        exclude (user.id ());
        include (user);
    }

    public List <User> users () {
        return users;
    }

    public User userById (Integer id)
    throws Exception {
        for (User user0: users)
            if (user0.id ().equals (id))
                return user0;
        throw new Exception ("user exists not");
    }

    private Boolean listContainsId (Integer id) {
        Boolean contains = false;
        for (User user : users)
            if (user.id ().equals (id))
                contains = true;
        return contains;
    }

    private User user (Integer id) {
        for (User user : users)
            if (user.id ().equals (id))
                return user;
        return null;
    }

}
