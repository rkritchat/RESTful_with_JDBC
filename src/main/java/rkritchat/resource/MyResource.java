package rkritchat.resource;

import rkritchat.constant.ResourceConstant;
import rkritchat.dao.UserDao;
import rkritchat.model.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class MyResource {
    private UserDao userDao = new UserDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return ResourceConstant.getGson().toJson(userDao.findAll());
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(UserInfo userInfo) {
        System.out.println(userInfo);
        return userDao.createUser(userInfo);
    }

    @PATCH
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(UserInfo userInfo) {
        System.out.println(userInfo);
        return userDao.updateUser(userInfo);
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(UserInfo userInfo) {
        return userDao.deleteUser(userInfo);
    }
}
