/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Pasaje;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.Viaje;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class PasajeJpaController {

    public PasajeJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasaje pasaje) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Viaje viaje = pasaje.getViaje();
            if (viaje != null) {
                viaje = em.getReference(viaje.getClass(), viaje.getNroviaje());
                pasaje.setViaje(viaje);
            }
            em.persist(pasaje);
            if (viaje != null) {
                viaje.getPasajeList().add(pasaje);
                viaje = em.merge(viaje);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasaje(pasaje.getNropasaje()) != null) {
                throw new PreexistingEntityException("Pasaje " + pasaje + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasaje pasaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasaje persistentPasaje = em.find(Pasaje.class, pasaje.getNropasaje());
            Viaje viajeOld = persistentPasaje.getViaje();
            Viaje viajeNew = pasaje.getViaje();
            if (viajeNew != null) {
                viajeNew = em.getReference(viajeNew.getClass(), viajeNew.getNroviaje());
                pasaje.setViaje(viajeNew);
            }
            pasaje = em.merge(pasaje);
            if (viajeOld != null && !viajeOld.equals(viajeNew)) {
                viajeOld.getPasajeList().remove(pasaje);
                viajeOld = em.merge(viajeOld);
            }
            if (viajeNew != null && !viajeNew.equals(viajeOld)) {
                viajeNew.getPasajeList().add(pasaje);
                viajeNew = em.merge(viajeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pasaje.getNropasaje();
                if (findPasaje(id) == null) {
                    throw new NonexistentEntityException("The pasaje with id " + id + " no longer exists.");
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
            Pasaje pasaje;
            try {
                pasaje = em.getReference(Pasaje.class, id);
                pasaje.getNropasaje();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasaje with id " + id + " no longer exists.", enfe);
            }
            Viaje viaje = pasaje.getViaje();
            if (viaje != null) {
                viaje.getPasajeList().remove(pasaje);
                viaje = em.merge(viaje);
            }
            em.remove(pasaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasaje> findPasajeEntities() {
        return findPasajeEntities(true, -1, -1);
    }

    public List<Pasaje> findPasajeEntities(int maxResults, int firstResult) {
        return findPasajeEntities(false, maxResults, firstResult);
    }

    private List<Pasaje> findPasajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasaje.class));
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

    public Pasaje findPasaje(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasaje> rt = cq.from(Pasaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
