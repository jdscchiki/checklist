/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.service.rest;

import com.jbadcode.checklist.persistence.entity.AppUser;
import javax.ejb.Stateful;

/**
 *
 * @author Juan David Segura
 */
@Stateful
public class SessionBean {

    private AppUser logedUser;
    
    public AppUser getLogedUser() {
        return logedUser;
    }

    public void setLogedUser(AppUser logedUser) {
        this.logedUser = logedUser;
    }
    
}
