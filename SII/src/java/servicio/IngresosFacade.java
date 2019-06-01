/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyectosii.Ingresos;

/**
 *
 * @author migov
 */
@Stateless
public class IngresosFacade extends AbstractFacade<Ingresos> {

    @PersistenceContext(unitName = "ProyectoSIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresosFacade() {
        super(Ingresos.class);
    }
    
}
