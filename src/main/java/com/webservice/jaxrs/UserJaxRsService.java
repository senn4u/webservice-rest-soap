package com.webservice.jaxrs;

import com.model.User;
import com.model.UserDTO;
import com.service.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.UnknownHostException;

/**
 * @author Ben Yasin
 */
@Service
@Path("/user")
public class UserJaxRsService {

    private static User user;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserJaxRsService.class);

    @Context
    private UriInfo uriInfo;
    @Resource
    private UserService userService;

    public UserJaxRsService() throws UnknownHostException{
        LOGGER.info("Creating a CRUD User");
        user = new User();
    }

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String findPlainText() {
        return "Hello Jersey";
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    @GET
    @Path("/html")
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response findUserById(@PathParam("id") ObjectId id){
        user = userService.findById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setId(String.valueOf(id));
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(userDTO).build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUserById(@PathParam("id") ObjectId id) {
        User user = userService.deleteById(id);
        LOGGER.info("Deleted a user successfully with id " + user.getId());
        return Response.noContent().status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInJSON(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user = userService.save(user);
        LOGGER.info("Created a user successfully with id " + user.getId());
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @POST
    @Path("/postForm")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)//MultivaluedMap formParams
    public Response createInForm(@FormParam("name") String name,
                             @FormParam("age") int age,
                             @FormParam("position") String position,
                             @FormParam("interest") String interest
                             ) {
        user = new User();
        user.setName(name);
        user.setAge(age);
        user.setPosition(position);
        user.setInterest(interest);
        user = userService.save(user);
        LOGGER.info("Created a user successfully with id " + user.getId());
        return Response.status(Response.Status.OK).build();
    }

}
