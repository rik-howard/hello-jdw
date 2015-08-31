
package hello.mockito.first.usage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserDAOTest {

    private UserDAO testee;
    private User user;

    @Before
    public void setUp ()
    throws Exception {
        testee = new UserDAO ();
        user = new User (0, "rik");
    }

    @After
    public void tearDown ()
    throws Exception {
        testee = null;
        user = null;
    }

    @Test
    public void testInclude ()
    throws Exception {
        assert testee.users ().isEmpty ();
        testee.include (user);
        assert testee.users ().size () == 1;
        assert testee.users ().get (0) == user;
        try {
            testee.include (new User (user.id (), "Lyo"));
        }
        catch (Exception e) {
            assert e.getMessage ().equals ("user exists");
        }
    }

    @Test
    public void testExclude ()
    throws Exception {
        assert testee.users ().isEmpty ();
        testee.include (user);
        assert ! testee.users ().isEmpty ();
        testee.exclude (user.id ());
        assert testee.users ().isEmpty ();
        try {
            testee.exclude (user.id ());
        }
        catch (Exception e) {
            assert e.getMessage ().equals ("user exists not");
        }
    }

    @Test
    public void testReclude ()
    throws Exception {
        assert testee.users ().isEmpty ();
        testee.include (user);
        User delta = new User (user.id (), "lyo");
        testee.reclude (delta);
        assert testee.users ().size () == 1;
        assert testee.users ().get (0) == delta;
    }

    @Test
    public void testUsers ()
    throws Exception {
        List <User> expected = Arrays.asList (user);
        testee.include (user);
        List <User> actual = testee.users ();
        assert expected.equals (actual);
    }

    @Test
    public void testUserById ()
    throws Exception {
        User expected = user;
        testee.include (user);
        User actual = testee.userById (user.id ());
        assert expected == actual;
        try {
            actual = testee.userById (1);
        }
        catch (Exception e) {
            assert e.getMessage ().equals ("user exists not");
        }
    }

}
