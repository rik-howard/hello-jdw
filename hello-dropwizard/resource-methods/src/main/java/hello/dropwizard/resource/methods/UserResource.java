
package hello.dropwizard.resource.methods;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path ("/users")
public class UserResource {

    private UserService userService;

    public UserResource (UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path ("")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getFromUsers () {
        return Response.ok (userService.users ()).build ();
    }

    @GET
    @Path ("/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getFromUserWithId (@PathParam ("id") Integer id) {
        try {
            assert RMValidator.validatesId (id);
            User user = userService.userWithId (id);
            return Response.ok (user).build ();
        }
        catch (Error e) {
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }
        catch (Exception e) {
            return Response.status (Response.Status.NOT_FOUND).build ();
        }
    }

    @POST
    @Path ("")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response postToUsers (User user) {
        try {
            assert RMValidator.validatesUser (user);
            userService.createUser (user);
            return Response
                .status (Response.Status.CREATED)
                .location (URI.create (String.format ("http://localhost:8080/users/%s", user.getId ())))
                .build ();
        }
        catch (Error e) {
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }
        catch (Exception e) {
            return Response.status (Response.Status.CONFLICT).build ();
        }
    }

    @POST
    @Path ("/{id}")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response postUserNot (@PathParam ("id") Integer id, User user) {
        try {
            assert RMValidator.validatesIdAndUser (id, user);
            user = userService.userWithId (user.getId ());
            return Response.status (Response.Status.CONFLICT).build ();
        }
        catch (Error e) {
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }
        catch (Exception e) {
            return Response.status (Response.Status.NOT_FOUND).build ();
        }
    }

    @PUT
    @Path ("")
    public Response putUsersNot (User user) {
        return Response.status (Response.Status.BAD_REQUEST).build ();
    }

    @PUT
    @Path ("/{id}")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response putUser (@PathParam ("id") Integer id, User user) {
        try {
            assert RMValidator.validatesIdAndUser (id, user);
            userService.updateUser (id, user);
            return Response.ok ().build ();
        }
        catch (Error e) {
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }
        catch (Exception e) {
            return Response.status (Response.Status.NOT_FOUND).build ();
        }

    }

    @DELETE
    @Path ("")
    public Response deleteUsersNot () {
        return Response.status (Response.Status.BAD_REQUEST).build ();
    }

    @DELETE
    @Path ("/{id}")
    public Response deleteUser (@PathParam ("id") Integer id) {
        try {
            assert RMValidator.validatesId (id);
            userService.deleteUser (id);
            return Response.ok ().build ();
        }
        catch (Error e) {
            return Response.status (Response.Status.BAD_REQUEST).build ();
        }
        catch (Exception e) {
            return Response.status (Response.Status.NOT_FOUND).build ();
        }
    }

}
