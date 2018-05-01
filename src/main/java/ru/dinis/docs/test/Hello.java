package ru.dinis.docs.test;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Create by dinis of 20.04.18.
 */
@Path("/hello")
public class Hello {
    @GET
    @Produces("application/json")
    public Bean getBean() {
        return new Bean(1, "dinis");
    }
}
