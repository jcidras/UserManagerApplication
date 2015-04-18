/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.UserEntity;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DataAccess;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
/**
 *
 * @author Jason
 */
@Path("/manager")
public class Resource {
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public List<UserEntity> getUsers() {        
        DataAccess dao = new DataAccess();
        List<UserEntity>users = dao.getUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/{id}")
    public UserEntity getUser(@PathParam("id") int id) {
        DataAccess dao = new DataAccess();
        UserEntity user = dao.getUser(id);   
        System.out.println(user);
        return user;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users/")
    public void addUser(UserEntity user) {        
        DataAccess dao = new DataAccess();
        int rowsAffected = dao.addUser(user);        
        if (rowsAffected > 0) {
            System.out.println("user added");
        }else {
            System.out.println("user was not added");
        }        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users/{id}")
    public void editUser(UserEntity user) {
        DataAccess dao = new DataAccess();
        int rowsAffected = dao.editUser(user);
        if (rowsAffected > 0) {
            System.out.println("user updated");
        } else {
            System.out.println("user not updated");
        }
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/users/{id}")
    public void deleteUser(@PathParam("id") int id) {
        DataAccess dao = new DataAccess();
        int rowsAffected = dao.deleteUser(id);
        if (rowsAffected > 0) {
            System.out.println("user deleted");
        } else {
            System.out.println("user not deleted");
        }
    }
}
