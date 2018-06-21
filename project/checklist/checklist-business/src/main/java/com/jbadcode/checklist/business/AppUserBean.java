/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.business;

import com.jbadcode.checklist.persistence.entity.AppUser;
import com.jbadcode.checklist.persistence.facede.AppUserFacade;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Juan David Segura
 */
@Stateless
@LocalBean
public class AppUserBean {

    @EJB
    private AppUserFacade appUserFacade;

    public AppUser authenticate(String nick, String password) {
//        try {

        AppUser appUser = appUserFacade.getByNick(nick);
        if (appUser.getPassword().equals(password)) {
            return appUser;
        } else {
//                throw new ApplicationException(UserError.ER_0000001.name());
            return null;
        }
//
//        } catch (NoResultException ex) {
//            throw new ApplicationException(
//                    UserError.ER_0000002.name());
//            
//        } catch (NonUniqueResultException ex) {
//            throw new ApplicationException(
//                    ApplicationError.ER_0000001.name());
//            
//        } catch (PersistenceException ex) {
//            throw new RollBackApplicationException(
//                    SystemError.ER_0000001.name(), 
//                    ex, true);
//        }
    }
}
