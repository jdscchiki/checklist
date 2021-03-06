/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.facede;

import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.persistence.entity.AppUser_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Juan David Segura
 */
@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "com.jbadcode_checklist-persistence_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppUserFacade() {
        super(AppUser.class);
    }
    
    public AppUser getByNick(String nick) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<AppUser> cq = cb.createQuery(AppUser.class);

        Root root = cq.from(AppUser.class);
        cq.
                select(root).
                where(
                        cb.equal(root.get(AppUser_.nick), nick));
        
        return em.
                createQuery(cq).
                getSingleResult();
    }
}
