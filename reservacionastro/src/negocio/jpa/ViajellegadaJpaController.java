/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Viajellegada;
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
public class ViajellegadaJpaController {

    public ViajellegadaJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Viajellegada viajellegada) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(viajellegada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findViajellegada(viajellegada.getId()) != null) {
                throw new PreexistingEntityException("Viajellegada " + viajellegada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Viajellegada viajellegada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            viajellegada = em.merge(viajellegada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = viajellegada.getId();
                if (findViajellegada(id) == null) {
                    throw new NonexistentEntityException("The viajellegada with id " + id + " no longer exists.");
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
            Viajellegada viajellegada;
            try {
                viajellegada = em.getReference(Viajellegada.class, id);
                viajellegada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The viajellegada with id " + id + " no longer exists.", enfe);
            }
            em.remove(viajellegada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Viajellegada> findViajellegadaEntities() {
        return findViajellegadaEntities(true, -1, -1);
    }

    public List<Viajellegada> findViajellegadaEntities(int maxResults, int firstResult) {
        return findViajellegadaEntities(false, maxResults, firstResult);
    }

    private List<Viajellegada> findViajellegadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Viajellegada.class));
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

    public Viajellegada findViajellegada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Viajellegada.class, id);
        } finally {
            em.close();
        }
    }

    public int getViajellegadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Viajellegada> rt = cq.from(Viajellegada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
