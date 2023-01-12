/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.UASpws;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ws.c.UASpws.exceptions.NonexistentEntityException;
import ws.c.UASpws.exceptions.PreexistingEntityException;

/**
 *
 * @author Abian
 */
public class FinaluasJpaController implements Serializable {

    public FinaluasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ws.c_UASpws_jar_0.0.1-SNAPSHOTPU");

    public FinaluasJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Finaluas finaluas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(finaluas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinaluas(finaluas.getId()) != null) {
                throw new PreexistingEntityException("Finaluas " + finaluas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Finaluas finaluas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            finaluas = em.merge(finaluas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = finaluas.getId();
                if (findFinaluas(id) == null) {
                    throw new NonexistentEntityException("The finaluas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Finaluas finaluas;
            try {
                finaluas = em.getReference(Finaluas.class, id);
                finaluas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finaluas with id " + id + " no longer exists.", enfe);
            }
            em.remove(finaluas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Finaluas> findFinaluasEntities() {
        return findFinaluasEntities(true, -1, -1);
    }

    public List<Finaluas> findFinaluasEntities(int maxResults, int firstResult) {
        return findFinaluasEntities(false, maxResults, firstResult);
    }

    private List<Finaluas> findFinaluasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Finaluas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Finaluas findFinaluas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Finaluas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinaluasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Finaluas> rt = cq.from(Finaluas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
