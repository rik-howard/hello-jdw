
package hello.mockito.first.usage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService testee;
    private List <User> users;
    private UserDAO userDAO;
    private User user;

    @Before
    public void setUp ()
    throws Exception {
        user = mock (User.class);
        userDAO = mock (UserDAO.class);
        users = Arrays.asList (user);
        testee = new UserService (userDAO);
    }

    @After
    public void tearDown ()
    throws Exception {
        testee = null;
        users = null;
        userDAO = null;
        user = null;
    }

    @Test
    public void testUsers ()
    throws Exception {
        when (userDAO.users ()).thenReturn (users);
        assert testee.users () == users;
    }

    @Test
    public void testUserWithId ()
    throws Exception {
        when (userDAO.userById (user.id ())).thenReturn (user);
        assert testee.userWithId (user.id ()) == user;
    }

    @Test
    public void testCreateUser ()
    throws Exception {
        doNothing ().when (userDAO).include (user);
        testee.createUser (user);
    }

    @Test
    public void testDeleteUser ()
    throws Exception {
        doNothing ().when (userDAO).exclude (1);
        testee.deleteUser (0);
    }

    @Test
    public void testUpdateUser ()
    throws Exception {
        User delta = mock (User.class);
        when (delta.id ()).thenReturn (0);
        doNothing ().when (userDAO).reclude (delta);
        testee.updateUser (0, delta);
    }

}
