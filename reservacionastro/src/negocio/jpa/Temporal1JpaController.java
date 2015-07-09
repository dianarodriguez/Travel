/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Temporal1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class Temporal1JpaController {

    public Temporal1JpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Temporal1 temporal1) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temporal1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemporal1(temporal1.getId()) != null) {
                throw new PreexistingEntityException("Temporal1 " + temporal1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Temporal1 temporal1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temporal1 = em.merge(temporal1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = temporal1.getId();
                if (findTemporal1(id) == null) {
                    throw new NonexistentEntityException("The temporal1 with id " + id + " no longer exists.");
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
            Temporal1 temporal1;
            try {
                temporal1 = em.getReference(Temporal1.class, id);
                temporal1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temporal1 with id " + id + " no longer exists.", enfe);
            }
            em.remove(temporal1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Temporal1> findTemporal1Entities() {
        return findTemporal1Entities(true, -1, -1);
    }

    public List<Temporal1> findTemporal1Entities(int maxResults, int firstResult) {
        return findTemporal1Entities(false, maxResults, firstResult);
    }

    private List<Temporal1> findTemporal1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temporal1.class));
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

    public Temporal1 findTemporal1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temporal1.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemporal1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temporal1> rt = cq.from(Temporal1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
