/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Listaespera;
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
public class ListaesperaJpaController {

    public ListaesperaJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Listaespera listaespera) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaespera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaespera(listaespera.getCi()) != null) {
                throw new PreexistingEntityException("Listaespera " + listaespera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Listaespera listaespera) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaespera = em.merge(listaespera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaespera.getCi();
                if (findListaespera(id) == null) {
                    throw new NonexistentEntityException("The listaespera with id " + id + " no longer exists.");
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
            Listaespera listaespera;
            try {
                listaespera = em.getReference(Listaespera.class, id);
                listaespera.getCi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaespera with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaespera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Listaespera> findListaesperaEntities() {
        return findListaesperaEntities(true, -1, -1);
    }

    public List<Listaespera> findListaesperaEntities(int maxResults, int firstResult) {
        return findListaesperaEntities(false, maxResults, firstResult);
    }

    private List<Listaespera> findListaesperaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Listaespera.class));
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

    public Listaespera findListaespera(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Listaespera.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaesperaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Listaespera> rt = cq.from(Listaespera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
