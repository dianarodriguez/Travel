/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.jpa;

import datos.Reservacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.Pasajesrectificados;
import datos.Viaje;
import java.util.ArrayList;
import negocio.jpa.exceptions.IllegalOrphanException;
import negocio.jpa.exceptions.NonexistentEntityException;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class ReservacionJpaController {

    public ReservacionJpaController() {
        emf = Persistence.createEntityManagerFactory("reservacionastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservacion reservacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajesrectificados pasajesrectificados = reservacion.getPasajesrectificados();
            if (pasajesrectificados != null) {
                pasajesrectificados = em.getReference(pasajesrectificados.getClass(), pasajesrectificados.getNroreserv());
                reservacion.setPasajesrectificados(pasajesrectificados);
            }
            Viaje viaje = reservacion.getViaje();
            if (viaje != null) {
                viaje = em.getReference(viaje.getClass(), viaje.getNroviaje());
                reservacion.setViaje(viaje);
            }
            em.persist(reservacion);
            if (pasajesrectificados != null) {
                Reservacion oldReservacionOfPasajesrectificados = pasajesrectificados.getReservacion();
                if (oldReservacionOfPasajesrectificados != null) {
                    oldReservacionOfPasajesrectificados.setPasajesrectificados(null);
                    oldReservacionOfPasajesrectificados = em.merge(oldReservacionOfPasajesrectificados);
                }
                pasajesrectificados.setReservacion(reservacion);
                pasajesrectificados = em.merge(pasajesrectificados);
            }
            if (viaje != null) {
                viaje.getReservacionList().add(reservacion);
                viaje = em.merge(viaje);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReservacion(reservacion.getNroreserv()) != null) {
                throw new PreexistingEntityException("Reservacion " + reservacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservacion reservacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservacion persistentReservacion = em.find(Reservacion.class, reservacion.getNroreserv());
            Pasajesrectificados pasajesrectificadosOld = persistentReservacion.getPasajesrectificados();
            Pasajesrectificados pasajesrectificadosNew = reservacion.getPasajesrectificados();
            Viaje viajeOld = persistentReservacion.getViaje();
            Viaje viajeNew = reservacion.getViaje();
            List<String> illegalOrphanMessages = null;
            if (pasajesrectificadosOld != null && !pasajesrectificadosOld.equals(pasajesrectificadosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Pasajesrectificados " + pasajesrectificadosOld + " since its reservacion field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pasajesrectificadosNew != null) {
                pasajesrectificadosNew = em.getReference(pasajesrectificadosNew.getClass(), pasajesrectificadosNew.getNroreserv());
                reservacion.setPasajesrectificados(pasajesrectificadosNew);
            }
            if (viajeNew != null) {
                viajeNew = em.getReference(viajeNew.getClass(), viajeNew.getNroviaje());
                reservacion.setViaje(viajeNew);
            }
            reservacion = em.merge(reservacion);
            if (pasajesrectificadosNew != null && !pasajesrectificadosNew.equals(pasajesrectificadosOld)) {
                Reservacion oldReservacionOfPasajesrectificados = pasajesrectificadosNew.getReservacion();
                if (oldReservacionOfPasajesrectificados != null) {
                    oldReservacionOfPasajesrectificados.setPasajesrectificados(null);
                    oldReservacionOfPasajesrectificados = em.merge(oldReservacionOfPasajesrectificados);
                }
                pasajesrectificadosNew.setReservacion(reservacion);
                pasajesrectificadosNew = em.merge(pasajesrectificadosNew);
            }
            if (viajeOld != null && !viajeOld.equals(viajeNew)) {
                viajeOld.getReservacionList().remove(reservacion);
                viajeOld = em.merge(viajeOld);
            }
            if (viajeNew != null && !viajeNew.equals(viajeOld)) {
                viajeNew.getReservacionList().add(reservacion);
                viajeNew = em.merge(viajeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservacion.getNroreserv();
                if (findReservacion(id) == null) {
                    throw new NonexistentEntityException("The reservacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservacion reservacion;
            try {
                reservacion = em.getReference(Reservacion.class, id);
                reservacion.getNroreserv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Pasajesrectificados pasajesrectificadosOrphanCheck = reservacion.getPasajesrectificados();
            if (pasajesrectificadosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reservacion (" + reservacion + ") cannot be destroyed since the Pasajesrectificados " + pasajesrectificadosOrphanCheck + " in its pasajesrectificados field has a non-nullable reservacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Viaje viaje = reservacion.getViaje();
            if (viaje != null) {
                viaje.getReservacionList().remove(reservacion);
                viaje = em.merge(viaje);
            }
            em.remove(reservacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservacion> findReservacionEntities() {
        return findReservacionEntities(true, -1, -1);
    }

    public List<Reservacion> findReservacionEntities(int maxResults, int firstResult) {
        return findReservacionEntities(false, maxResults, firstResult);
    }

    private List<Reservacion> findReservacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservacion.class));
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

    public Reservacion findReservacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservacion> rt = cq.from(Reservacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
