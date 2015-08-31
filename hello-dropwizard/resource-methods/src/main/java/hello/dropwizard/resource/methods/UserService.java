
package hello.dropwizard.resource.methods;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserService {

    @Valid @NotNull private UserDAO userDAO;

    public UserService (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    List <User> users () {
        return userDAO.users ();
    }

    User userWithId (Integer id)
    throws Exception {
        return userDAO.userById (id);
    }

    public void createUser (User user)
    throws Exception {
        userDAO.include (user);
    }

    public void deleteUser (Integer id)
    throws Exception {
        userDAO.exclude (id);
    }

    public void updateUser (Integer id, User user)
    throws Exception {
        assert user.getId ().equals (id);
        userDAO.reinclude (user);
    }

}
