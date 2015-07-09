/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Destinocc;
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
public class DestinoccJpaController {

    public DestinoccJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Destinocc destinocc) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(destinocc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDestinocc(destinocc.getNombre()) != null) {
                throw new PreexistingEntityException("Destinocc " + destinocc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Destinocc destinocc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            destinocc = em.merge(destinocc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = destinocc.getNombre();
                if (findDestinocc(id) == null) {
                    throw new NonexistentEntityException("The destinocc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Destinocc destinocc;
            try {
                destinocc = em.getReference(Destinocc.class, id);
                destinocc.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinocc with id " + id + " no longer exists.", enfe);
            }
            em.remove(destinocc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Destinocc> findDestinoccEntities() {
        return findDestinoccEntities(true, -1, -1);
    }

    public List<Destinocc> findDestinoccEntities(int maxResults, int firstResult) {
        return findDestinoccEntities(false, maxResults, firstResult);
    }

    private List<Destinocc> findDestinoccEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Destinocc.class));
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

    public Destinocc findDestinocc(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Destinocc.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestinoccCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Destinocc> rt = cq.from(Destinocc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
