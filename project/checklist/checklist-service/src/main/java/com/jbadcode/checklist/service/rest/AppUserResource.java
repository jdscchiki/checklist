/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest;

import com.jbadcode.checklist.business.AppUserBean;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.service.rest.util.ExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Juan David Segura
 */
@Path("AppUser")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppUserResource {

    @EJB
    private AppUserBean appUserBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AppUserResource
     */
    public AppUserResource() {
    }

    private static final Logger logger = Logger.getLogger(AppUserResource.class.getName());

    /**
     * Retrieves representation of an instance of
     * com.jbadcode.checklist.service.rest.AppUserResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/authenticate")
    public Response authenticate(AppUser appUser) {
        try {
            return Response.
                    ok(appUserBean.
                            authenticate(appUser.getNick(), appUser.getPassword())).
                    build();
        } catch (Exception e) {
            ExceptionHandler.throwWebApplicationException(e);
        }
        return null;
    }
}
