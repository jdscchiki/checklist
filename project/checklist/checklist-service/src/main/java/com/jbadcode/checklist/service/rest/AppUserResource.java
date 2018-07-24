/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest;

import com.jbadcode.checklist.service.rest.ejb.SessionBean;
import com.jbadcode.checklist.business.AppUserBean;
import com.jbadcode.checklist.log.exception.ApplicationException;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.service.rest.config.RequestProperties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
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
    private SessionBean sessionBean;

    @EJB
    private AppUserBean appUserBean;

    @Context
    private ContainerRequestContext containerRequest;

    /**
     * Creates a new instance of AppUserResource
     */
    public AppUserResource() {
    }

    @PostConstruct
    public void init() {
        appUserBean.setProcessIdentificator(
                containerRequest.getProperty(
                        RequestProperties.PROCESS_IDENTIFICAROR.name()).toString());
    }

    /**
     * Give Authentication and return user data,
     * if the user nick and password are correct
     *
     * @param appUser AppUser whit nick and password
     * @return an instance of AppUser
     * @throws ApplicationException returns exception codes 
     * <ul>
     * <li>UE_000_001</li>
     * <li>UE_000_002</li>
     * <li>AE_000_001</li>
     * <li>SE_000_001</li>
     * <li>SE_000_002</li>
     * </ul>
     * @throws Exception invalid user input data or database failure
     */
    @POST
    @Path("/authenticate")
    public AppUser authenticate(AppUser appUser) throws ApplicationException, Exception {
        appUser = appUserBean.
                authenticate(appUser.getNick(), appUser.getPassword());
        sessionBean.startSession(appUser);
        return appUser;
    }
    
    /**
     * Registers the user's credentials, if the nick has not yet been used
     *
     * @param appUser
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @PUT
    @Path("/register")
    public AppUser register(AppUser appUser) throws ApplicationException, Exception {
        appUser = appUserBean.
                register(appUser);
        return appUser;
    }

    @DELETE
    @Path("/invalidate")
    public Response invalidate() {
        sessionBean.endSession();
        return Response.ok().build();
    }
}
