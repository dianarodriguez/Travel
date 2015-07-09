/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Pasajesrectificados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.Reservacion;
import java.util.ArrayList;
import negocio.jpa.exceptions.IllegalOrphanException;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class PasajesrectificadosJpaController {

    public PasajesrectificadosJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasajesrectificados pasajesrectificados) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Reservacion reservacionOrphanCheck = pasajesrectificados.getReservacion();
        if (reservacionOrphanCheck != null) {
            Pasajesrectificados oldPasajesrectificadosOfReservacion = reservacionOrphanCheck.getPasajesrectificados();
            if (oldPasajesrectificadosOfReservacion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Reservacion " + reservacionOrphanCheck + " already has an item of type Pasajesrectificados whose reservacion column cannot be null. Please make another selection for the reservacion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservacion reservacion = pasajesrectificados.getReservacion();
            if (reservacion != null) {
                reservacion = em.getReference(reservacion.getClass(), reservacion.getNroreserv());
                pasajesrectificados.setReservacion(reservacion);
            }
            em.persist(pasajesrectificados);
            if (reservacion != null) {
                reservacion.setPasajesrectificados(pasajesrectificados);
                reservacion = em.merge(reservacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasajesrectificados(pasajesrectificados.getNroreserv()) != null) {
                throw new PreexistingEntityException("Pasajesrectificados " + pasajesrectificados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasajesrectificados pasajesrectificados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajesrectificados persistentPasajesrectificados = em.find(Pasajesrectificados.class, pasajesrectificados.getNroreserv());
            Reservacion reservacionOld = persistentPasajesrectificados.getReservacion();
            Reservacion reservacionNew = pasajesrectificados.getReservacion();
            List<String> illegalOrphanMessages = null;
            if (reservacionNew != null && !reservacionNew.equals(reservacionOld)) {
                Pasajesrectificados oldPasajesrectificadosOfReservacion = reservacionNew.getPasajesrectificados();
                if (oldPasajesrectificadosOfReservacion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Reservacion " + reservacionNew + " already has an item of type Pasajesrectificados whose reservacion column cannot be null. Please make another selection for the reservacion field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (reservacionNew != null) {
                reservacionNew = em.getReference(reservacionNew.getClass(), reservacionNew.getNroreserv());
                pasajesrectificados.setReservacion(reservacionNew);
            }
            pasajesrectificados = em.merge(pasajesrectificados);
            if (reservacionOld != null && !reservacionOld.equals(reservacionNew)) {
                reservacionOld.setPasajesrectificados(null);
                reservacionOld = em.merge(reservacionOld);
            }
            if (reservacionNew != null && !reservacionNew.equals(reservacionOld)) {
                reservacionNew.setPasajesrectificados(pasajesrectificados);
                reservacionNew = em.merge(reservacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pasajesrectificados.getNroreserv();
                if (findPasajesrectificados(id) == null) {
                    throw new NonexistentEntityException("The pasajesrectificados with id " + id + " no longer exists.");
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
            Pasajesrectificados pasajesrectificados;
            try {
                pasajesrectificados = em.getReference(Pasajesrectificados.class, id);
                pasajesrectificados.getNroreserv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasajesrectificados with id " + id + " no longer exists.", enfe);
            }
            Reservacion reservacion = pasajesrectificados.getReservacion();
            if (reservacion != null) {
                reservacion.setPasajesrectificados(null);
                reservacion = em.merge(reservacion);
            }
            em.remove(pasajesrectificados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasajesrectificados> findPasajesrectificadosEntities() {
        return findPasajesrectificadosEntities(true, -1, -1);
    }

    public List<Pasajesrectificados> findPasajesrectificadosEntities(int maxResults, int firstResult) {
        return findPasajesrectificadosEntities(false, maxResults, firstResult);
    }

    private List<Pasajesrectificados> findPasajesrectificadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasajesrectificados.class));
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

    public Pasajesrectificados findPasajesrectificados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasajesrectificados.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasajesrectificadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasajesrectificados> rt = cq.from(Pasajesrectificados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
