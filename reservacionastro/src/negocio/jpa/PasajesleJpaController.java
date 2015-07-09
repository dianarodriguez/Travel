/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Pasajesle;
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
public class PasajesleJpaController {

    public PasajesleJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasajesle pasajesle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pasajesle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasajesle(pasajesle.getIdpasajesle()) != null) {
                throw new PreexistingEntityException("Pasajesle " + pasajesle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasajesle pasajesle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pasajesle = em.merge(pasajesle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pasajesle.getIdpasajesle();
                if (findPasajesle(id) == null) {
                    throw new NonexistentEntityException("The pasajesle with id " + id + " no longer exists.");
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
            Pasajesle pasajesle;
            try {
                pasajesle = em.getReference(Pasajesle.class, id);
                pasajesle.getIdpasajesle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasajesle with id " + id + " no longer exists.", enfe);
            }
            em.remove(pasajesle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasajesle> findPasajesleEntities() {
        return findPasajesleEntities(true, -1, -1);
    }

    public List<Pasajesle> findPasajesleEntities(int maxResults, int firstResult) {
        return findPasajesleEntities(false, maxResults, firstResult);
    }

    private List<Pasajesle> findPasajesleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasajesle.class));
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

    public Pasajesle findPasajesle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasajesle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasajesleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasajesle> rt = cq.from(Pasajesle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
