/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest;

import com.jbadcode.checklist.service.rest.ejb.SessionBean;
import com.jbadcode.checklist.business.AppUserBean;
import com.jbadcode.checklist.entityfiltering.appuser.PasswordView;
import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.service.rest.config.RequestProperties;
import java.lang.annotation.Annotation;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
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
        appUserBean.setProcessIdentificator(containerRequest.getProperty(RequestProperties.PROCESS_IDENTIFICAROR.name()).toString());
    }

    /**
     * Retrieves representation of an instance of
     * com.jbadcode.checklist.service.rest.AppUserResource
     *
     * @param appUser
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @POST
    @Path("/authenticate")
    public Response authenticate(AppUser appUser) throws Exception {
        appUser = appUserBean.
                authenticate(appUser.getNick(), appUser.getPassword());
        sessionBean.startSession(appUser);
        return Response.
                ok().
//                entity(appUser, new Annotation[]{
//                    PasswordView.Factory.get()
//                }).
                entity(appUser, new Annotation[0]).
                build();
    }

    @DELETE
    @Path("/invalidate")
    public Response invalidate() {
        sessionBean.endSession();
        return Response.ok().build();
    }
}
