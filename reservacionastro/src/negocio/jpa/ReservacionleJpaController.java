/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Reservacionle;
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
public class ReservacionleJpaController {

    public ReservacionleJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservacionle reservacionle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservacionle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReservacionle(reservacionle.getId()) != null) {
                throw new PreexistingEntityException("Reservacionle " + reservacionle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservacionle reservacionle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservacionle = em.merge(reservacionle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservacionle.getId();
                if (findReservacionle(id) == null) {
                    throw new NonexistentEntityException("The reservacionle with id " + id + " no longer exists.");
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
            Reservacionle reservacionle;
            try {
                reservacionle = em.getReference(Reservacionle.class, id);
                reservacionle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservacionle with id " + id + " no longer exists.", enfe);
            }
            em.remove(reservacionle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservacionle> findReservacionleEntities() {
        return findReservacionleEntities(true, -1, -1);
    }

    public List<Reservacionle> findReservacionleEntities(int maxResults, int firstResult) {
        return findReservacionleEntities(false, maxResults, firstResult);
    }

    private List<Reservacionle> findReservacionleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservacionle.class));
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

    public Reservacionle findReservacionle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservacionle.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservacionleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservacionle> rt = cq.from(Reservacionle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
