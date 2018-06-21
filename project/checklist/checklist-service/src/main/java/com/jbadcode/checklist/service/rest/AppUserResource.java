/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest;

import com.jbadcode.checklist.business.AppUserBean;
import com.jbadcode.checklist.persistence.entity.AppUser;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Juan David Segura
 */
@Path("AppUser")
@Stateless
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

    /**
     * Retrieves representation of an instance of com.jbadcode.checklist.service.rest.AppUserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AppUser getJson() {
        return appUserBean.authenticate("admin", "admin");
    }
}
