/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.facede;

import com.jbadcode.checklist.persistence.entity.ChecklistOwner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan David Segura
 */
@Stateless
public class ChecklistOwnerFacade extends AbstractFacade<ChecklistOwner> {

    @PersistenceContext(unitName = "com.jbadcode_checklist-persistence_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChecklistOwnerFacade() {
        super(ChecklistOwner.class);
    }
    
}
