/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Destinoriente;
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
public class DestinorienteJpaController {

    public DestinorienteJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Destinoriente destinoriente) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(destinoriente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDestinoriente(destinoriente.getNombre()) != null) {
                throw new PreexistingEntityException("Destinoriente " + destinoriente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Destinoriente destinoriente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            destinoriente = em.merge(destinoriente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = destinoriente.getNombre();
                if (findDestinoriente(id) == null) {
                    throw new NonexistentEntityException("The destinoriente with id " + id + " no longer exists.");
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
            Destinoriente destinoriente;
            try {
                destinoriente = em.getReference(Destinoriente.class, id);
                destinoriente.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinoriente with id " + id + " no longer exists.", enfe);
            }
            em.remove(destinoriente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Destinoriente> findDestinorienteEntities() {
        return findDestinorienteEntities(true, -1, -1);
    }

    public List<Destinoriente> findDestinorienteEntities(int maxResults, int firstResult) {
        return findDestinorienteEntities(false, maxResults, firstResult);
    }

    private List<Destinoriente> findDestinorienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Destinoriente.class));
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

    public Destinoriente findDestinoriente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Destinoriente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestinorienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Destinoriente> rt = cq.from(Destinoriente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
