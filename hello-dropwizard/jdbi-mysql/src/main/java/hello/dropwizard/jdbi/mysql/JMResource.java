
package hello.dropwizard.jdbi.mysql;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path ("/jdbi-mysql")
public class JMResource {

    private JMDAO jmDAO;

    public JMResource () {}

    public JMResource (JMDAO jmDAO) {
        this.jmDAO = jmDAO;
    }

    public JMDAO getJmDAO () {
        return jmDAO;
    }

    @GET @Path ("/id")
    public String getIdByName (@QueryParam ("name") String name) {
        return jmDAO.findIdByName (name).toString ();
    }

    @GET @Path ("/user")
    public String getUserById (@QueryParam ("id") Integer id) {
        return jmDAO.findUserById (id).toHTML ();
    }

    @GET @Path ("/new-user")
    public String setUserByName (@QueryParam ("name") String name) {
        jmDAO.insert (name);
        Integer id = jmDAO.findIdByName (name);
        return jmDAO.findUserById (id).toHTML ();
    }

    @GET @Path ("/old-user")
    public String jetUserByName (@QueryParam ("name") String name) {
        jmDAO.delete (name);
        return "okay";
    }

    @GET @Path ("/names")
    public String getNames () {
        String returnee = new String ();
        List <String> names = jmDAO.findNames ();
        for (String name: names)
            if (returnee.isEmpty ()) returnee = name;
            else returnee += " " + name;
        return returnee;
    }

    @GET @Path ("/users")
    public String getUsers () {
        String returnee = new String ();
        List <JMPOJO> users = jmDAO.findUsers ();
        for (JMPOJO user: users)
            returnee += user.toHTML ();
        return returnee;
    }

    @GET @Path ("/reset-user")
    public String resetUser (@QueryParam ("id") Integer id, @QueryParam ("name") String name) {
        jmDAO.update (id, name);
        return jmDAO.findUserById (id).toHTML ();
    }

}
